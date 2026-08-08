package com.vas.study.datagen;

import com.vas.study.MyStudyMod;
import com.vas.study.block.ModBlocks;
import com.vas.study.item.ModItems;
import com.vas.study.recipe.AlloyFurnaceRecipe;
import com.vas.study.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static final List<ItemLike> OBSIDIAN_INGOT_LIST = List.of(
            Items.OBSIDIAN,
            Items.CRYING_OBSIDIAN
    );

    @Override
    public @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput output) {
        return new RecipeProvider(provider, output) {
            @Override
            public void buildRecipes() {
                oreSmelting(OBSIDIAN_INGOT_LIST, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.OBSIDIAN_INGOT, 0.7f, 200, "obsidian_ingot");
                oreBlasting(OBSIDIAN_INGOT_LIST, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.OBSIDIAN_INGOT, 0.7f, 200, "obsidian_ingot");
                shaped(RecipeCategory.FOOD, ModItems.OBSIDIAN_APPLE, 1)
                        .pattern(" # ")
                        .pattern("#*#")
                        .pattern(" # ")
                        .define('#', ModItems.OBSIDIAN_INGOT)
                        .define('*', Items.APPLE)
                        .unlockedBy("has_obsidian_ingot", has(ModItems.OBSIDIAN_INGOT))
                        .unlockedBy("has_apple", has(Items.APPLE))
                        .save(output);
//                simpleCookingRecipe("smelting", CampfireCookingRecipe::new, 200, Items.OBSIDIAN, ModItems.OBSIDIAN_INGOT, 1.0f);
                twoByTwoPacker(RecipeCategory.MISC, ModBlocks.OBSIDIAN_BLOCK, ModItems.OBSIDIAN_INGOT);
                shapeless(RecipeCategory.MISC, ModItems.OBSIDIAN_INGOT, 4)
                        .requires(ModBlocks.OBSIDIAN_BLOCK)
                        .unlockedBy("has_obsidian_block", has(ModBlocks.OBSIDIAN_BLOCK))
                        .save(output);
//                nineBlockStorageRecipes(RecipeCategory.MISC, ModBlocks.OBSIDIAN_BLOCK, RecipeCategory.MISC, ModItems.OBSIDIAN_INGOT);
                shaped(RecipeCategory.FOOD, ModItems.REINFORCED_OBSIDIAN_APPLE, 1)
                        .pattern("###")
                        .pattern("#*#")
                        .pattern("###")
                        .define('#', ModBlocks.OBSIDIAN_BLOCK)
                        .define('*', ModItems.OBSIDIAN_APPLE)
                        .unlockedBy("has_obsidian_ingot", has(ModItems.OBSIDIAN_INGOT))
                        .unlockedBy("has_obsidian_apple", has(ModItems.OBSIDIAN_APPLE))
                        .save(output);
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REINFORCED_OBSIDIAN, 1)
                        .pattern("###")
                        .pattern("#*#")
                        .pattern("###")
                        .define('#', ModBlocks.OBSIDIAN_BLOCK)
                        .define('*', ModItemTags.OBSIDIAN_ITEMS)
                        .unlockedBy("has_obsidian_ingot", has(ModItems.OBSIDIAN_INGOT))
                        .unlockedBy("has_obsidian", has(ModItemTags.OBSIDIAN_ITEMS))
                        .save(output);
                shaped(RecipeCategory.MISC, ModItems.OBSIDIAN_COAL, 8)
                        .pattern("###")
                        .pattern("#*#")
                        .pattern("###")
                        .define('#', ItemTags.COALS)
                        .define('*', ModItems.OBSIDIAN_INGOT)
                        .unlockedBy("has_obsidian_ingot", has(ModItems.OBSIDIAN_INGOT))
                        .unlockedBy("has_coal", has(ItemTags.COALS))
                        .save(output);
                // Obsidian Armor — crafted pieces come with Fire Protection I
                var fireProtection = registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_PROTECTION);
                obsidianArmorShaped("obsidian_helmet", fireProtection, ModItems.OBSIDIAN_HELMET, "###", "# #");
                obsidianArmorShaped("obsidian_chestplate", fireProtection, ModItems.OBSIDIAN_CHESTPLATE, "# #", "###", "###");
                obsidianArmorShaped("obsidian_leggings", fireProtection, ModItems.OBSIDIAN_LEGGINGS, "###", "# #", "# #");
                obsidianArmorShaped("obsidian_boots", fireProtection, ModItems.OBSIDIAN_BOOTS, "# #", "# #");
                // Alloy Furnace — the machine itself
                shaped(RecipeCategory.DECORATIONS, ModBlocks.ALLOY_FURNACE, 1)
                        .pattern("OIO")
                        .pattern("O#O")
                        .pattern("OOO")
                        .define('O', ModItems.OBSIDIAN_INGOT)
                        .define('I', Items.IRON_INGOT)
                        .define('#', Items.FURNACE)
                        .unlockedBy("has_obsidian_ingot", has(ModItems.OBSIDIAN_INGOT))
                        .unlockedBy("has_furnace", has(Items.FURNACE))
                        .save(output);
                // Alloy Furnace recipes (order-sensitive: slot 0, 1, 2; per-slot consume counts)
                alloyFurnaceRecipe("obsidian_alloy_ingot", ModItems.OBSIDIAN_INGOT, ModItems.OBSIDIAN_ALLOY_INGOT,
                        2, 2.0F, 300, null, List.of(
                                Ingredient.of(ModItems.OBSIDIAN_INGOT),
                                Ingredient.of(Items.IRON_INGOT),
                                Ingredient.of(Items.GOLD_INGOT)));
                alloyFurnaceRecipe("reinforced_obsidian_from_alloy", ModBlocks.OBSIDIAN_BLOCK, ModBlocks.REINFORCED_OBSIDIAN,
                        1, 5.0F, 600, null, List.of(
                                Ingredient.of(ModBlocks.OBSIDIAN_BLOCK),
                                Ingredient.of(Items.IRON_INGOT),
                                Ingredient.of(Items.GOLD_INGOT)));
                // Rose gold: 3 gold + 1 copper -> 4 rose gold ingots.
                // Matching is order-insensitive, so the gold may sit in one slot (x3) or be
                // spread across two (x2 + x1); any slot arrangement works. Gold and copper may
                // also be smelted individually, so the alloy check runs before the smelting fallback.
                // The material lists live in item tags (ingots, ores, raw ores) so adding a new
                // gold or copper source is a tag edit, not a recipe edit.
                alloyFurnaceRecipe("rose_gold_ingot", Items.GOLD_INGOT, ModItems.ROSE_GOLD_INGOT,
                        4, 1.5F, 200, List.of(3, 1), List.of(
                                Ingredient.of(registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModItemTags.GOLD_MATERIALS)),
                                Ingredient.of(registries.lookupOrThrow(Registries.ITEM).getOrThrow(ModItemTags.COPPER_MATERIALS))));
            }

            /**
             * Builds an Alloy Furnace recipe with order-insensitive ingredients and its advancement.
             * {@code consumes} holds how many items each ingredient needs (null = one each);
             * it must line up with {@code ingredients}.
             */
            private void alloyFurnaceRecipe(String name, ItemLike unlockItem, ItemLike result, int count, float experience, int cookingTime, List<Integer> consumes, List<Ingredient> ingredients) {
                ItemStackTemplate template = new ItemStackTemplate(result.asItem(), count);
                AlloyFurnaceRecipe recipe = new AlloyFurnaceRecipe(
                        new net.minecraft.world.item.crafting.Recipe.CommonInfo(true),
                        new AlloyFurnaceRecipe.AlloyBookInfo(RecipeBookCategories.CRAFTING_MISC, ""),
                        ingredients,
                        consumes != null ? consumes : List.of(),
                        template,
                        experience,
                        cookingTime
                );

                ResourceKey<Recipe<?>> key = ResourceKey.create(Registries.RECIPE, MyStudyMod.withMODID(name));
                RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
                advancementBuilder.unlockedBy("has_unlock_item", has(unlockItem));
                output.accept(key, recipe, advancementBuilder.build(output, key, RecipeCategory.MISC));
            }

            /** Builds a shaped recipe whose result carries the given enchantment (e.g. Fire Protection I). */
            private void obsidianArmorShaped(String name, net.minecraft.core.Holder<net.minecraft.world.item.enchantment.Enchantment> enchantment, ItemLike result, String... pattern) {
                // Build the enchantment component directly — `new ItemStack(result)` is not usable here
                // because item components are not bound yet during datagen.
                ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                enchantments.set(enchantment, 2);
                ItemStackTemplate template = new ItemStackTemplate(result.asItem(),
                        DataComponentPatch.builder()
                                .set(DataComponents.ENCHANTMENTS, enchantments.toImmutable())
                                .build());

                ShapedRecipePattern shapedPattern = ShapedRecipePattern.of(
                        Map.of('#', Ingredient.of(ModItems.OBSIDIAN_INGOT)),
                        List.of(pattern)
                );

                ResourceKey<net.minecraft.world.item.crafting.Recipe<?>> key =
                        ResourceKey.create(Registries.RECIPE, MyStudyMod.withMODID(name));

                RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
                advancementBuilder.unlockedBy("has_obsidian_ingot", has(ModItems.OBSIDIAN_INGOT));

                ShapedRecipe recipe = new ShapedRecipe(
                        RecipeBuilder.createCraftingCommonInfo(true),
                        RecipeBuilder.createCraftingBookInfo(RecipeCategory.COMBAT, ""),
                        shapedPattern,
                        template
                );

                output.accept(key, recipe, advancementBuilder.build(output, key, RecipeCategory.COMBAT));
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "";
    }
}
