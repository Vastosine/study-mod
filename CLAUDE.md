# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

A learning mod ("My Study Mod") for **Fabric 1.26.1 / Java 25** (`fabric-api 0.145.1+26.1`, loader `0.19.3`), MOD_ID `study`, package `com.vas.study`. Content so far: obsidian-themed items/armor/food, and the **Alloy Furnace** — a machine with a custom 3-ingredient recipe system. The user is learning modding; keep comments clear and educational, match surrounding style.

## Commands (Windows + Git Bash)

The machine's default JDK is not Java 25, so **every Gradle command needs the explicit JAVA_HOME**:

```bash
JAVA_HOME="/d/programs/Minecraft/Java/zulu25.32.21-ca-jdk25.0.2-win_x64" ./gradlew runClient    # run the game
JAVA_HOME="/d/programs/Minecraft/Java/zulu25.32.21-ca-jdk25.0.2-win_x64" ./gradlew runDatagen  # regenerate data
JAVA_HOME="/d/programs/Minecraft/Java/zulu25.32.21-ca-jdk25.0.2-win_x64" ./gradlew build       # compile + jar
```

- **Never combine `runDatagen` and `build` in one Gradle invocation** — Gradle 9.5 rejects `sourcesJar`'s implicit dependency on the regenerated `src/main/generated` (validation error). Run them as separate commands, datagen first.
- No tests and no lint tasks exist (`test` is NO-SOURCE).
- Datagen output lands in `src/main/generated` and is git-tracked — commit regenerated JSON alongside recipe/tag changes.
- Proxy is configured in `gradle.properties` (127.0.0.1:7890); only relevant when resolving new dependencies.

## Architecture

### Registration pattern
Content is registered in static `Mod*` classes (`ModItems`, `ModBlocks`, `ModBlockEntities`, `ModMenuTypes`, `ModRecipeTypes`, `ModRecipeSerializers`, `ModCreativeModeTabs`), each with an `onInitialize()` called in order from `MyStudyMod.onInitialize` (items → tabs → blocks → block entities → menus → recipe types → serializers). Resource IDs are built with `MyStudyMod.withMODID(name)`; in 1.26.1 the ID class is `net.minecraft.resources.Identifier`. Client-only registration (screens) lives in `MyStudyModClient` via `MenuScreens.register`.

### Datagen
`MyStudyModDataGenerator` wires six providers: lang, models, recipes, block tags, loot tables, item tags. Vanilla-style data (recipes, loot, tags) is declared in `datagen/` providers, not hand-written — edit the provider and rerun `runDatagen`.

### The Alloy Furnace (the machine)
Layers, in dependency order:
- **Recipe**: `AlloyFurnaceRecipe` (record: `ingredients` + parallel `counts` + `result` `ItemStackTemplate` + `experience` + `cookingtime`) with `MAP_CODEC`/`STREAM_CODEC`, registered via `ModRecipeTypes`/`ModRecipeSerializers` under `study:alloy_furnace`. Matching is **order-insensitive**: `allocate(input)` merges duplicate ingredient entries, checks material totals across the three slots, and returns the per-slot consume plan (null = no match); `matches()` delegates to it. `AlloyRecipeInput` is the 3-slot `RecipeInput`.
- **Block entity**: `AlloyFurnaceBlockEntity` extends `BaseContainerBlockEntity` + `WorldlyContainer`. Slots: 0-2 ingredients, 3 fuel, 4 output. `serverTick` picks a recipe every tick: alloy first (only if its result fits the output slot, `canBurn`), else the first input slot whose smelting recipe isn't blocked by the output — a blocked higher-priority recipe must not stop a burnable lower-priority one. Ingredient totals use `study:gold_materials` / `study:copper_materials` tags. Smelting time is 2/5 of vanilla, fuel burn 1/2. Hopper mapping (direction = container→hopper): left→slot 0, up→slot 1, right→slot 2, back→fuel, down→output (take only), front→none.
- **Progress reset logic**: the BE tracks `currentRecipe` (a `ResourceKey<Recipe<?>>`, set when a cook completes). `setItem` recomputes `findRecipe` (same "can it burn" rule as serverTick) and resets the timer only when the recipe id actually changes — including when a **count drop** makes the current recipe unmatchable (e.g. taking 2 gold out of 3 mid-cook). Unrelated slot changes keep progress.
- **Persistence**: item contents are saved automatically by `BaseContainerBlockEntity` (CONTAINER component); the four int fields use `loadAdditional`/`saveAdditional` with `ValueInput`/`ValueOutput` (`getShortOr`/`putShort`, snake_case names like vanilla).
- **Block**: `AlloyFurnaceBlock` extends `AbstractFurnaceBlock` (gives LIT/FACING, `use`→open menu, redstone signal); implements `codec()`, `newBlockEntity`, `getTicker` (capturing-lambda form, not a bare method reference), `openContainer`.
- **Menu/Screen**: `AlloyFurnaceMenu` (plain `AbstractContainerMenu`, deliberately not `RecipeBookMenu`/`AbstractFurnaceMenu` — vanilla's are closed to custom 3-input recipes) with vanilla `Slot`s; `AlloyFurnaceScreen` extends `AbstractContainerScreen` and draws the flame/arrow sprites via `blitSprite`.

## 1.26.1 API notes (verified against the merged sources jar)

The version has renamed/moved several APIs vs. 1.20-1.21 tutorials — don't "fix" them:
- `ResourceLocation` → `net.minecraft.resources.Identifier`; codecs use `MapCodec`, `Recipe.CommonInfo`/`BookInfo`, `ItemStackTemplate`, `Ingredient.CODEC`/`CONTENTS_STREAM_CODEC`.
- Container NBT: `CompoundTag` → `ValueInput`/`ValueOutput`; fuel: `level.fuelValues().isFuel(...)` / `burnDuration(...)`.
- `Ingredient.of` has only `ItemLike...` and single-arg `HolderSet<Item>` overloads — no mixed tag+item overload. Tag ingredients: `Ingredient.of(registries.lookupOrThrow(Registries.ITEM).getOrThrow(TagKey))`.
- Java records: in a compact constructor you must assign plain parameter names (`counts = List.of(...)`), never `this.counts = ...`.
- Several private/package-private vanilla members are used through Fabric's transitive access widener (`MenuType` constructor, `MenuScreens.register`, `BlockModelGenerators.createFurnace`, …) — already working, don't remove.
- The merged vanilla sources jar for API lookup: `.gradle/loom-cache/minecraftMaven/net/minecraft/minecraft-merged-a26c9a9f3c/26.1/...-sources.jar`.

## Assets

Textures are **not** generated by datagen: they are hand-recolored from vanilla via throwaway Java scripts (see `%TEMP%\alloytex`, JDK 25) that extract sprites from the client jar and remap brightness levels to the obsidian palette (warm orange pixels preserved). The Alloy Furnace GUI/block/item textures and the gui sprites in `assets/study/textures/...` are maintained this way. JSON model/blockstate files, recipes, tags, loot, and lang are datagen output.
