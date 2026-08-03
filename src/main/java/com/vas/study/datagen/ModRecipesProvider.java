package com.vas.study.datagen;

import com.vas.study.block.ModBlocks;
import com.vas.study.item.ModItems;
import com.vas.study.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.List;
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
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "";
    }
}
