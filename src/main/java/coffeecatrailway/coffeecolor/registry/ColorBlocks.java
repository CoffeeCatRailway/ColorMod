package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.biome.feature.tree.*;
import coffeecatrailway.coffeecolor.common.block.*;
import coffeecatrailway.coffeecolor.common.item.ColorGemItem;
import coffeecatrailway.coffeecolor.registrate.ColorRegistrate;
import coffeecatrailway.coffeecolor.registrate.ColorTags;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.*;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorBlocks {

    public static final RegistryEntry<Block> COLOR_DIRT = REGISTRATE.object("color_dirt").block(Block::new)
            .defaultBlockstate().defaultLoot().defaultLang().tag(Tags.Blocks.DIRT)
            .initialProperties(Material.EARTH, MaterialColor.DIRT).properties(prop -> prop.hardnessAndResistance(0.5F).sound(SoundType.GROUND)).simpleItem().register();

    public static final RegistryEntry<ColorGrassBlock> COLOR_GRASS_BLOCK = REGISTRATE.object("color_grass_block").block(ColorGrassBlock::new)
            .blockstate(NonNullBiConsumer.noop()).defaultLang().tag(Tags.Blocks.DIRT)
            .loot((lootTables, block) -> lootTables.registerLootTable(block, RegistrateBlockLootTables.droppingWithSilkTouch(block, COLOR_DIRT.get())))
            .initialProperties(Material.ORGANIC, MaterialColor.GRASS).properties(prop -> prop.tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).simpleItem().register();

    private static NonNullUnaryOperator<Block.Properties> GRASS_PROPS = prop -> prop.sound(SoundType.PLANT).doesNotBlockMovement().hardnessAndResistance(0.0f).notSolid();

    public static final RegistryEntry<ColorDoubleGrassBlock> TALL_COLOR_GRASS = REGISTRATE.object("tall_color_grass").block(ColorDoubleGrassBlock::new)
            .blockstate((ctx, provider) -> provider.getVariantBuilder(ctx.getEntry())
                    .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                    .modelForState().modelFile(provider.models().getExistingFile(ColorMod.getLocation("block/color_tall_grass_bottom"))).addModel()
                    .partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                    .modelForState().modelFile(provider.models().getExistingFile(ColorMod.getLocation("block/color_tall_grass_top"))).addModel()
            ).defaultLoot().defaultLang().initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().model((ctx, provider) -> provider.generated(ctx::getEntry, ColorMod.getLocation("block/color_tall_grass_bottom"))).build().register();

    public static final RegistryEntry<ColorTallGrassBlock> COLOR_GRASS = REGISTRATE.object("color_grass").block(ColorTallGrassBlock::new)
            .blockstate((ctx, provider) -> provider.simpleBlock(ctx.getEntry(), provider.models().getExistingFile(ColorMod.getLocation("block/color_grass"))))
            .defaultLoot().defaultLang().initialProperties(Material.TALL_PLANTS, Material.TALL_PLANTS.getColor()).properties(GRASS_PROPS)
            .item().model((ctx, provider) -> provider.generated(ctx::getEntry, ColorMod.getLocation("block/color_grass"))).build().register();

    public static final RegistryEntry<ColorPortalBlock> COLOR_PORTAL = REGISTRATE.block("color_portal", ColorPortalBlock::new)
            .blockstate(NonNullBiConsumer.noop()).defaultLang().tag(BlockTags.PORTALS)
            .initialProperties(Material.PORTAL, Material.PORTAL.getColor())
            .properties(prop -> prop.doesNotBlockMovement().harvestTool(ToolType.PICKAXE).hardnessAndResistance(-1.0F, 3000000.0F).lightValue(10).sound(SoundType.GLASS).notSolid())
            .register();

    private static final NonNullUnaryOperator<Block.Properties> LOG_PROPS = prop -> prop.hardnessAndResistance(2.0F).sound(SoundType.WOOD);

    public static final RegistryEntry<ColorLogBlock> WHITE_LOG = registerLog("white_log", DyeColor.WHITE, ColorTags.Blocks.WHITE_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> ORANGE_LOG = registerLog("orange_log", DyeColor.ORANGE, ColorTags.Blocks.ORANGE_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> MAGENTA_LOG = registerLog("magenta_log", DyeColor.MAGENTA, ColorTags.Blocks.MAGENTA_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> LIGHT_BLUE_LOG = registerLog("light_blue_log", DyeColor.LIGHT_BLUE, ColorTags.Blocks.LIGHT_BLUE_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> YELLOW_LOG = registerLog("yellow_log", DyeColor.YELLOW, ColorTags.Blocks.YELLOW_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> LIME_LOG = registerLog("lime_log", DyeColor.LIME, ColorTags.Blocks.LIME_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> PINK_LOG = registerLog("pink_log", DyeColor.PINK, ColorTags.Blocks.PINK_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> GRAY_LOG = registerLog("gray_log", DyeColor.GRAY, ColorTags.Blocks.GRAY_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> LIGHT_GRAY_LOG = registerLog("light_gray_log", DyeColor.LIGHT_GRAY, ColorTags.Blocks.LIGHT_GRAY_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> CYAN_LOG = registerLog("cyan_log", DyeColor.CYAN, ColorTags.Blocks.CYAN_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> PURPLE_LOG = registerLog("purple_log", DyeColor.PURPLE, ColorTags.Blocks.PURPLE_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> BLUE_LOG = registerLog("blue_log", DyeColor.BLUE, ColorTags.Blocks.BLUE_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> BROWN_LOG = registerLog("brown_log", DyeColor.BROWN, ColorTags.Blocks.BROWN_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> GREEN_LOG = registerLog("green_log", DyeColor.GREEN, ColorTags.Blocks.GREEN_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> RED_LOG = registerLog("red_log", DyeColor.RED, ColorTags.Blocks.RED_LOGS, false, false, null);
    public static final RegistryEntry<ColorLogBlock> BLACK_LOG = registerLog("black_log", DyeColor.BLACK, ColorTags.Blocks.BLACK_LOGS, false, false, null);

    public static final RegistryEntry<ColorLogBlock> STRIPPED_WHITE_LOG = registerLog("stripped_white_log", DyeColor.WHITE, ColorTags.Blocks.WHITE_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_ORANGE_LOG = registerLog("stripped_orange_log", DyeColor.ORANGE, ColorTags.Blocks.ORANGE_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_MAGENTA_LOG = registerLog("stripped_magenta_log", DyeColor.MAGENTA, ColorTags.Blocks.MAGENTA_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_LIGHT_BLUE_LOG = registerLog("stripped_light_blue_log", DyeColor.LIGHT_BLUE, ColorTags.Blocks.LIGHT_BLUE_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_YELLOW_LOG = registerLog("stripped_yellow_log", DyeColor.YELLOW, ColorTags.Blocks.YELLOW_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_LIME_LOG = registerLog("stripped_lime_log", DyeColor.LIME, ColorTags.Blocks.LIME_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_PINK_LOG = registerLog("stripped_pink_log", DyeColor.PINK, ColorTags.Blocks.PINK_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_GRAY_LOG = registerLog("stripped_gray_log", DyeColor.GRAY, ColorTags.Blocks.GRAY_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_LIGHT_GRAY_LOG = registerLog("stripped_light_gray_log", DyeColor.LIGHT_GRAY, ColorTags.Blocks.LIGHT_GRAY_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_CYAN_LOG = registerLog("stripped_cyan_log", DyeColor.CYAN, ColorTags.Blocks.CYAN_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_PURPLE_LOG = registerLog("stripped_purple_log", DyeColor.PURPLE, ColorTags.Blocks.PURPLE_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_BLUE_LOG = registerLog("stripped_blue_log", DyeColor.BLUE, ColorTags.Blocks.BLUE_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_BROWN_LOG = registerLog("stripped_brown_log", DyeColor.BROWN, ColorTags.Blocks.BROWN_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_GREEN_LOG = registerLog("stripped_green_log", DyeColor.GREEN, ColorTags.Blocks.GREEN_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_RED_LOG = registerLog("stripped_red_log", DyeColor.RED, ColorTags.Blocks.RED_LOGS, true, false, null);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_BLACK_LOG = registerLog("stripped_black_log", DyeColor.BLACK, ColorTags.Blocks.BLACK_LOGS, true, false, null);

    public static final RegistryEntry<ColorLogBlock> WHITE_WOOD = registerLog("white_wood", DyeColor.WHITE, ColorTags.Blocks.WHITE_LOGS, false, true, WHITE_LOG);
    public static final RegistryEntry<ColorLogBlock> ORANGE_WOOD = registerLog("orange_wood", DyeColor.ORANGE, ColorTags.Blocks.ORANGE_LOGS, false, true, ORANGE_LOG);
    public static final RegistryEntry<ColorLogBlock> MAGENTA_WOOD = registerLog("magenta_wood", DyeColor.MAGENTA, ColorTags.Blocks.MAGENTA_LOGS, false, true, MAGENTA_LOG);
    public static final RegistryEntry<ColorLogBlock> LIGHT_BLUE_WOOD = registerLog("light_blue_wood", DyeColor.LIGHT_BLUE, ColorTags.Blocks.LIGHT_BLUE_LOGS, false, true, LIGHT_BLUE_LOG);
    public static final RegistryEntry<ColorLogBlock> YELLOW_WOOD = registerLog("yellow_wood", DyeColor.YELLOW, ColorTags.Blocks.YELLOW_LOGS, false, true, YELLOW_LOG);
    public static final RegistryEntry<ColorLogBlock> LIME_WOOD = registerLog("lime_wood", DyeColor.LIME, ColorTags.Blocks.LIME_LOGS, false, true, LIME_LOG);
    public static final RegistryEntry<ColorLogBlock> PINK_WOOD = registerLog("pink_wood", DyeColor.PINK, ColorTags.Blocks.PINK_LOGS, false, true, PINK_LOG);
    public static final RegistryEntry<ColorLogBlock> GRAY_WOOD = registerLog("gray_wood", DyeColor.GRAY, ColorTags.Blocks.GRAY_LOGS, false, true, GRAY_LOG);
    public static final RegistryEntry<ColorLogBlock> LIGHT_GRAY_WOOD = registerLog("light_gray_wood", DyeColor.LIGHT_GRAY, ColorTags.Blocks.LIGHT_GRAY_LOGS, false, true, LIGHT_GRAY_LOG);
    public static final RegistryEntry<ColorLogBlock> CYAN_WOOD = registerLog("cyan_wood", DyeColor.CYAN, ColorTags.Blocks.CYAN_LOGS, false, true, CYAN_LOG);
    public static final RegistryEntry<ColorLogBlock> PURPLE_WOOD = registerLog("purple_wood", DyeColor.PURPLE, ColorTags.Blocks.PURPLE_LOGS, false, true, PURPLE_LOG);
    public static final RegistryEntry<ColorLogBlock> BLUE_WOOD = registerLog("blue_wood", DyeColor.BLUE, ColorTags.Blocks.BLUE_LOGS, false, true, BLUE_LOG);
    public static final RegistryEntry<ColorLogBlock> BROWN_WOOD = registerLog("brown_wood", DyeColor.BROWN, ColorTags.Blocks.BROWN_LOGS, false, true, BROWN_LOG);
    public static final RegistryEntry<ColorLogBlock> GREEN_WOOD = registerLog("green_wood", DyeColor.GREEN, ColorTags.Blocks.GREEN_LOGS, false, true, GREEN_LOG);
    public static final RegistryEntry<ColorLogBlock> RED_WOOD = registerLog("red_wood", DyeColor.RED, ColorTags.Blocks.RED_LOGS, false, true, RED_LOG);
    public static final RegistryEntry<ColorLogBlock> BLACK_WOOD = registerLog("black_wood", DyeColor.BLACK, ColorTags.Blocks.BLACK_LOGS, false, true, BLACK_LOG);

    public static final RegistryEntry<ColorLogBlock> STRIPPED_WHITE_WOOD = registerLog("stripped_white_wood", DyeColor.WHITE, ColorTags.Blocks.WHITE_LOGS, true, true, STRIPPED_WHITE_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_ORANGE_WOOD = registerLog("stripped_orange_wood", DyeColor.ORANGE, ColorTags.Blocks.ORANGE_LOGS, true, true, STRIPPED_ORANGE_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_MAGENTA_WOOD = registerLog("stripped_magenta_wood", DyeColor.MAGENTA, ColorTags.Blocks.MAGENTA_LOGS, true, true, STRIPPED_MAGENTA_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_LIGHT_BLUE_WOOD = registerLog("stripped_light_blue_wood", DyeColor.LIGHT_BLUE, ColorTags.Blocks.LIGHT_BLUE_LOGS, true, true, STRIPPED_LIGHT_BLUE_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_YELLOW_WOOD = registerLog("stripped_yellow_wood", DyeColor.YELLOW, ColorTags.Blocks.YELLOW_LOGS, true, true, STRIPPED_YELLOW_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_LIME_WOOD = registerLog("stripped_lime_wood", DyeColor.LIME, ColorTags.Blocks.LIME_LOGS, true, true, STRIPPED_LIME_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_PINK_WOOD = registerLog("stripped_pink_wood", DyeColor.PINK, ColorTags.Blocks.PINK_LOGS, true, true, STRIPPED_PINK_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_GRAY_WOOD = registerLog("stripped_gray_wood", DyeColor.GRAY, ColorTags.Blocks.GRAY_LOGS, true, true, STRIPPED_GRAY_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_LIGHT_GRAY_WOOD = registerLog("stripped_light_gray_wood", DyeColor.LIGHT_GRAY, ColorTags.Blocks.LIGHT_GRAY_LOGS, true, true, STRIPPED_LIGHT_GRAY_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_CYAN_WOOD = registerLog("stripped_cyan_wood", DyeColor.CYAN, ColorTags.Blocks.CYAN_LOGS, true, true, STRIPPED_CYAN_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_PURPLE_WOOD = registerLog("stripped_purple_wood", DyeColor.PURPLE, ColorTags.Blocks.PURPLE_LOGS, true, true, STRIPPED_PURPLE_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_BLUE_WOOD = registerLog("stripped_blue_wood", DyeColor.BLUE, ColorTags.Blocks.BLUE_LOGS, true, true, STRIPPED_BLUE_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_BROWN_WOOD = registerLog("stripped_brown_wood", DyeColor.BROWN, ColorTags.Blocks.BROWN_LOGS, true, true, STRIPPED_BROWN_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_GREEN_WOOD = registerLog("stripped_green_wood", DyeColor.GREEN, ColorTags.Blocks.GREEN_LOGS, true, true, STRIPPED_GREEN_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_RED_WOOD = registerLog("stripped_red_wood", DyeColor.RED, ColorTags.Blocks.RED_LOGS, true, true, STRIPPED_RED_LOG);
    public static final RegistryEntry<ColorLogBlock> STRIPPED_BLACK_WOOD = registerLog("stripped_black_wood", DyeColor.BLACK, ColorTags.Blocks.BLACK_LOGS, true, true, STRIPPED_BLACK_LOG);

    public static final RegistryEntry<ColorSaplingBlock> WHITE_SAPLING = registerSapling("white_sapling", DyeColor.WHITE, new WhiteTree());
    public static final RegistryEntry<ColorSaplingBlock> ORANGE_SAPLING = registerSapling("orange_sapling", DyeColor.ORANGE, new OrangeTree());
    public static final RegistryEntry<ColorSaplingBlock> MAGENTA_SAPLING = registerSapling("magenta_sapling", DyeColor.MAGENTA, new MagentaTree());
    public static final RegistryEntry<ColorSaplingBlock> LIGHT_BLUE_SAPLING = registerSapling("light_blue_sapling", DyeColor.LIGHT_BLUE, new LightBlueTree());
    public static final RegistryEntry<ColorSaplingBlock> YELLOW_SAPLING = registerSapling("yellow_sapling", DyeColor.YELLOW, new YellowTree());
    public static final RegistryEntry<ColorSaplingBlock> LIME_SAPLING = registerSapling("lime_sapling", DyeColor.LIME, new LimeTree());
    public static final RegistryEntry<ColorSaplingBlock> PINK_SAPLING = registerSapling("pink_sapling", DyeColor.PINK, new PinkTree());
    public static final RegistryEntry<ColorSaplingBlock> GRAY_SAPLING = registerSapling("gray_sapling", DyeColor.GRAY, new GrayTree());
    public static final RegistryEntry<ColorSaplingBlock> LIGHT_GRAY_SAPLING = registerSapling("light_gray_sapling", DyeColor.LIGHT_GRAY, new LightGrayTree());
    public static final RegistryEntry<ColorSaplingBlock> CYAN_SAPLING = registerSapling("cyan_sapling", DyeColor.CYAN, new CyanTree());
    public static final RegistryEntry<ColorSaplingBlock> PURPLE_SAPLING = registerSapling("purple_sapling", DyeColor.PURPLE, new PurpleTree());
    public static final RegistryEntry<ColorSaplingBlock> BLUE_SAPLING = registerSapling("blue_sapling", DyeColor.BLUE, new BlueTree());
    public static final RegistryEntry<ColorSaplingBlock> BROWN_SAPLING = registerSapling("brown_sapling", DyeColor.BROWN, new BrownTree());
    public static final RegistryEntry<ColorSaplingBlock> GREEN_SAPLING = registerSapling("green_sapling", DyeColor.GREEN, new GreenTree());
    public static final RegistryEntry<ColorSaplingBlock> RED_SAPLING = registerSapling("red_sapling", DyeColor.RED, new RedTree());
    public static final RegistryEntry<ColorSaplingBlock> BLACK_SAPLING = registerSapling("black_sapling", DyeColor.BLACK, new BlackTree());

    public static final RegistryEntry<ColorLeavesBlock> WHITE_LEAVES = registerLeaves("white_leaves", DyeColor.WHITE, WHITE_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> ORANGE_LEAVES = registerLeaves("orange_leaves", DyeColor.ORANGE, ORANGE_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> MAGENTA_LEAVES = registerLeaves("magenta_leaves", DyeColor.MAGENTA, MAGENTA_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> LIGHT_BLUE_LEAVES = registerLeaves("light_blue_leaves", DyeColor.LIGHT_BLUE, LIGHT_BLUE_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> YELLOW_LEAVES = registerLeaves("yellow_leaves", DyeColor.YELLOW, YELLOW_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> LIME_LEAVES = registerLeaves("lime_leaves", DyeColor.LIME, LIME_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> PINK_LEAVES = registerLeaves("pink_leaves", DyeColor.PINK, PINK_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> GRAY_LEAVES = registerLeaves("gray_leaves", DyeColor.GRAY, GRAY_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> LIGHT_GRAY_LEAVES = registerLeaves("light_gray_leaves", DyeColor.LIGHT_GRAY, LIGHT_GRAY_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> CYAN_LEAVES = registerLeaves("cyan_leaves", DyeColor.CYAN, CYAN_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> PURPLE_LEAVES = registerLeaves("purple_leaves", DyeColor.PURPLE, PURPLE_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> BLUE_LEAVES = registerLeaves("blue_leaves", DyeColor.BLUE, BLUE_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> BROWN_LEAVES = registerLeaves("brown_leaves", DyeColor.BROWN, BROWN_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> GREEN_LEAVES = registerLeaves("green_leaves", DyeColor.GREEN, GREEN_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> RED_LEAVES = registerLeaves("red_leaves", DyeColor.RED, RED_SAPLING);
    public static final RegistryEntry<ColorLeavesBlock> BLACK_LEAVES = registerLeaves("black_leaves", DyeColor.BLACK, BLACK_SAPLING);

    private static final NonNullUnaryOperator<Block.Properties> PLANKS_PROPS = prop -> prop.hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD);

    public static final RegistryEntry<ColorPlanksBlock> WHITE_PLANKS = registerPlanks("white_planks", DyeColor.WHITE, ColorTags.Items.WHITE_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> ORANGE_PLANKS = registerPlanks("orange_planks", DyeColor.ORANGE, ColorTags.Items.ORANGE_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> MAGENTA_PLANKS = registerPlanks("magenta_planks", DyeColor.MAGENTA, ColorTags.Items.MAGENTA_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> LIGHT_BLUE_PLANKS = registerPlanks("light_blue_planks", DyeColor.LIGHT_BLUE, ColorTags.Items.LIGHT_BLUE_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> YELLOW_PLANKS = registerPlanks("yellow_planks", DyeColor.YELLOW, ColorTags.Items.YELLOW_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> LIME_PLANKS = registerPlanks("lime_planks", DyeColor.LIME, ColorTags.Items.LIME_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> PINK_PLANKS = registerPlanks("pink_planks", DyeColor.PINK, ColorTags.Items.PINK_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> GRAY_PLANKS = registerPlanks("gray_planks", DyeColor.GRAY, ColorTags.Items.GRAY_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> LIGHT_GRAY_PLANKS = registerPlanks("light_gray_planks", DyeColor.LIGHT_GRAY, ColorTags.Items.LIGHT_GRAY_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> CYAN_PLANKS = registerPlanks("cyan_planks", DyeColor.CYAN, ColorTags.Items.CYAN_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> PURPLE_PLANKS = registerPlanks("purple_planks", DyeColor.PURPLE, ColorTags.Items.PURPLE_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> BLUE_PLANKS = registerPlanks("blue_planks", DyeColor.BLUE, ColorTags.Items.BLUE_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> BROWN_PLANKS = registerPlanks("brown_planks", DyeColor.BROWN, ColorTags.Items.BROWN_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> GREEN_PLANKS = registerPlanks("green_planks", DyeColor.GREEN, ColorTags.Items.GREEN_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> RED_PLANKS = registerPlanks("red_planks", DyeColor.RED, ColorTags.Items.RED_LOGS);
    public static final RegistryEntry<ColorPlanksBlock> BLACK_PLANKS = registerPlanks("black_planks", DyeColor.BLACK, ColorTags.Items.BLACK_LOGS);

    public static final RegistryEntry<ColorStairsBlock> WHITE_STAIRS = registerStairs("white_stairs", WHITE_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> ORANGE_STAIRS = registerStairs("orange_stairs", ORANGE_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> MAGENTA_STAIRS = registerStairs("magenta_stairs", MAGENTA_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> LIGHT_BLUE_STAIRS = registerStairs("light_blue_stairs", LIGHT_BLUE_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> YELLOW_STAIRS = registerStairs("yellow_stairs", YELLOW_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> LIME_STAIRS = registerStairs("lime_stairs", LIME_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> PINK_STAIRS = registerStairs("pink_stairs", PINK_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> GRAY_STAIRS = registerStairs("gray_stairs", GRAY_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> LIGHT_GRAY_STAIRS = registerStairs("light_gray_stairs", LIGHT_GRAY_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> CYAN_STAIRS = registerStairs("cyan_stairs", CYAN_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> PURPLE_STAIRS = registerStairs("purple_stairs", PURPLE_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> BLUE_STAIRS = registerStairs("blue_stairs", BLUE_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> BROWN_STAIRS = registerStairs("brown_stairs", BROWN_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> GREEN_STAIRS = registerStairs("green_stairs", GREEN_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> RED_STAIRS = registerStairs("red_stairs", RED_PLANKS);
    public static final RegistryEntry<ColorStairsBlock> BLACK_STAIRS = registerStairs("black_stairs", BLACK_PLANKS);

    public static final RegistryEntry<ColorSlabBlock> WHITE_SLAB = registerSlab("white_slab", WHITE_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> ORANGE_SLAB = registerSlab("orange_slab", ORANGE_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> MAGENTA_SLAB = registerSlab("magenta_slab", MAGENTA_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> LIGHT_BLUE_SLAB = registerSlab("light_blue_slab", LIGHT_BLUE_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> YELLOW_SLAB = registerSlab("yellow_slab", YELLOW_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> LIME_SLAB = registerSlab("lime_slab", LIME_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> PINK_SLAB = registerSlab("pink_slab", PINK_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> GRAY_SLAB = registerSlab("gray_slab", GRAY_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> LIGHT_GRAY_SLAB = registerSlab("light_gray_slab", LIGHT_GRAY_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> CYAN_SLAB = registerSlab("cyan_slab", CYAN_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> PURPLE_SLAB = registerSlab("purple_slab", PURPLE_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> BLUE_SLAB = registerSlab("blue_slab", BLUE_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> BROWN_SLAB = registerSlab("brown_slab", BROWN_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> GREEN_SLAB = registerSlab("green_slab", GREEN_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> RED_SLAB = registerSlab("red_slab", RED_PLANKS);
    public static final RegistryEntry<ColorSlabBlock> BLACK_SLAB = registerSlab("black_slab", BLACK_PLANKS);

    public static final RegistryEntry<ColorGemOreBlock> WHITE_GEM_ORE = registerGemOre("white_gem_ore", DyeColor.WHITE, () -> ColorItems.WHITE_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> ORANGE_GEM_ORE = registerGemOre("orange_gem_ore", DyeColor.ORANGE, () -> ColorItems.ORANGE_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> MAGENTA_GEM_ORE = registerGemOre("magenta_gem_ore", DyeColor.MAGENTA, () -> ColorItems.MAGENTA_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> LIGHT_BLUE_GEM_ORE = registerGemOre("light_blue_gem_ore", DyeColor.LIGHT_BLUE, () -> ColorItems.LIGHT_BLUE_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> YELLOW_GEM_ORE = registerGemOre("yellow_gem_ore", DyeColor.YELLOW, () -> ColorItems.YELLOW_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> LIME_GEM_ORE = registerGemOre("lime_gem_ore", DyeColor.LIME, () -> ColorItems.LIME_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> PINK_GEM_ORE = registerGemOre("pink_gem_ore", DyeColor.PINK, () -> ColorItems.PINK_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> GRAY_GEM_ORE = registerGemOre("gray_gem_ore", DyeColor.GRAY, () -> ColorItems.GRAY_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> LIGHT_GRAY_GEM_ORE = registerGemOre("light_gray_gem_ore", DyeColor.LIGHT_GRAY, () -> ColorItems.LIGHT_GRAY_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> CYAN_GEM_ORE = registerGemOre("cyan_gem_ore", DyeColor.CYAN, () -> ColorItems.CYAN_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> PURPLE_GEM_ORE = registerGemOre("purple_gem_ore", DyeColor.PURPLE, () -> ColorItems.PURPLE_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> BLUE_GEM_ORE = registerGemOre("blue_gem_ore", DyeColor.BLUE, () -> ColorItems.BLUE_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> BROWN_GEM_ORE = registerGemOre("brown_gem_ore", DyeColor.BROWN, () -> ColorItems.BROWN_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> GREEN_GEM_ORE = registerGemOre("green_gem_ore", DyeColor.GREEN, () -> ColorItems.GREEN_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> RED_GEM_ORE = registerGemOre("red_gem_ore", DyeColor.RED, () -> ColorItems.RED_GEM.get());
    public static final RegistryEntry<ColorGemOreBlock> BLACK_GEM_ORE = registerGemOre("black_gem_ore", DyeColor.BLACK, () -> ColorItems.BLACK_GEM.get());

    public static void load() {
        ColorMod.LOGGER.info("Register blocks");
    }

    private static RegistryEntry<ColorLogBlock> registerLog(String id, DyeColor color, Tag<Block> tag, boolean stripped, boolean bark, Supplier<ColorLogBlock> woodRecipeBlock) {
        ItemBuilder<BlockItem, BlockBuilder<ColorLogBlock, ColorRegistrate>> logItemBuilder = REGISTRATE.object(id).block(prop -> new ColorLogBlock(color, prop))
                .blockstate((ctx, provider) -> {
                    ModelFile model = getLogWoodModel(ctx, provider.models(), stripped, bark, false);
                    provider.getVariantBuilder(ctx.getEntry())
                            .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                            .modelForState().modelFile(model).addModel()
                            .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                            .modelForState().modelFile(model).rotationX(90).addModel()
                            .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                            .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel();
                }).defaultLoot().defaultLang()
                .tag(tag).initialProperties(Material.WOOD, MaterialColor.WOOD).properties(LOG_PROPS)
                .item().model((ctx, provider) -> getLogWoodModel(ctx, provider, stripped, bark, true));
        Tag<Item> tagItem = ColorTags.getLogTagFromBlock(tag);
        if (tagItem != null)
            logItemBuilder.tag(tagItem);

        BlockBuilder<ColorLogBlock, ColorRegistrate> log = logItemBuilder.build();
        if (woodRecipeBlock != null)
            log = log.recipe((ctx, provider) -> ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 3)
                    .patternLine("LL")
                    .patternLine("LL")
                    .key('L', woodRecipeBlock.get())
                    .addCriterion("has_log", provider.hasItem(woodRecipeBlock.get())).build(provider));
        return log.register();
    }

    private static <M extends ModelProvider<?>, C extends DataGenContext<?, ?>> ModelFile getLogWoodModel(C ctx, M models, boolean stripped, boolean bark, boolean itemModel) {
        ModelFile model;
        if (itemModel) {
            if (bark)
                model = stripped ? models.withExistingParent(ctx.getName(), ColorMod.getLocation("block/stripped_color_wood"))
                        : models.withExistingParent(ctx.getName(), ColorMod.getLocation("block/color_wood"));
            else
                model = stripped ? models.withExistingParent(ctx.getName(), ColorMod.getLocation("block/stripped_color_log"))
                        : models.withExistingParent(ctx.getName(), ColorMod.getLocation("block/color_log_default"));
        } else {
            if (bark)
                model = stripped ? models.getExistingFile(ColorMod.getLocation("block/stripped_color_wood"))
                        : models.getExistingFile(ColorMod.getLocation("block/color_wood"));
            else
                model = stripped ? models.getExistingFile(ColorMod.getLocation("block/stripped_color_log"))
                        : models.getExistingFile(ColorMod.getLocation("block/color_log_default"));
        }
        return model;
    }

    private static RegistryEntry<ColorSaplingBlock> registerSapling(String id, DyeColor color, ColorTree tree) {
        return REGISTRATE.object(id).block(prop -> new ColorSaplingBlock(color, tree, prop))
                .blockstate((ctx, provider) -> provider.simpleBlock(ctx.getEntry(), provider.models().getExistingFile(ColorMod.getLocation("block/color_sapling"))))
                .defaultLoot().defaultLang().tag(BlockTags.SAPLINGS)
                .initialProperties(Material.PLANTS, Material.PLANTS.getColor()).properties(prop -> prop.doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f).sound(SoundType.PLANT).notSolid())
                .item().model((ctx, provider) -> provider.generated(ctx::getEntry, ColorMod.getLocation("block/color_sapling"))).tag(ItemTags.SAPLINGS).build().register();
    }

    private static RegistryEntry<ColorLeavesBlock> registerLeaves(String id, DyeColor color, Supplier<ColorSaplingBlock> sapling) {
        return REGISTRATE.object(id).block(prop -> new ColorLeavesBlock(color, prop))
                .blockstate((ctx, provider) -> provider.simpleBlock(ctx.getEntry(), provider.models().getExistingFile(ColorMod.getLocation("block/color_leaves"))))
                .loot((lootTables, block) ->
                        lootTables.registerLootTable(block, LootTable.builder()
                                .addLootPool(LootPool.builder()
                                        .rolls(new RandomValueRange(1))
                                        .addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(block)
                                                .acceptCondition(Alternative.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS)))
                                                        .alternative(MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))))))))
                                        .addEntry(ItemLootEntry.builder(sapling.get())
                                                .acceptCondition(SurvivesExplosion.builder())
                                                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.05f, 0.0625f, 0.083333336f, 0.1f)))
                                ).addLootPool(LootPool.builder()
                                        .rolls(new RandomValueRange(1))
                                        .addEntry(ItemLootEntry.builder(Items.STICK)
                                                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f))
                                                .acceptFunction(SetCount.builder(new RandomValueRange(1.0f, 2.0f))).acceptFunction(ExplosionDecay.builder()))
                                        .acceptCondition(Inverted.builder(Alternative.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS)))
                                                .alternative(MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))))))))
                ).defaultLang()
                .tag(BlockTags.LEAVES).initialProperties(Material.LEAVES, MaterialColor.WOOD).properties(prop -> prop.hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid())
                .item().model((ctx, provider) -> provider.withExistingParent(ctx.getName(), ColorMod.getLocation("block/color_leaves"))).tag(ItemTags.LEAVES).build().register();
    }

    private static RegistryEntry<ColorPlanksBlock> registerPlanks(String id, DyeColor color, Tag<Item> logTag) {
        return REGISTRATE.object(id).block(prop -> new ColorPlanksBlock(color, prop))
                .blockstate((ctx, provider) -> provider.simpleBlock(ctx.getEntry(), provider.models().getExistingFile(ColorMod.getLocation("block/color_planks")))).defaultLoot().defaultLang()
                .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx.getEntry(), 4).addIngredient(logTag).addCriterion("has_log", provider.hasItem(logTag)).build(provider))
                .tag(BlockTags.PLANKS).initialProperties(Material.WOOD, MaterialColor.WOOD).properties(PLANKS_PROPS)
                .item().model((ctx, provider) -> provider.withExistingParent(ctx.getName(), ColorMod.getLocation("block/color_planks"))).tag(ItemTags.PLANKS).build().register();
    }

    private static RegistryEntry<ColorStairsBlock> registerStairs(String id, Supplier<ColorPlanksBlock> planks) {
        return REGISTRATE.object(id).block(prop -> new ColorStairsBlock(planks, prop))
                .blockstate((ctx, provider) -> provider.stairsBlock(ctx.getEntry(),
                        provider.models().getExistingFile(ColorMod.getLocation("block/color_stairs")),
                        provider.models().getExistingFile(ColorMod.getLocation("block/color_inner_stairs")),
                        provider.models().getExistingFile(ColorMod.getLocation("block/color_outer_stairs")))
                ).defaultLoot().defaultLang().recipe((ctx, provider) -> provider.stairs(DataIngredient.items(planks.get()), ctx::getEntry, "wooden_stairs", false))
                .initialProperties(Material.WOOD, MaterialColor.WOOD).properties(PLANKS_PROPS)
                .item().model((ctx, provider) -> provider.withExistingParent(ctx.getName(), ColorMod.getLocation("block/color_stairs"))).build().register();
    }

    private static RegistryEntry<ColorSlabBlock> registerSlab(String id, Supplier<ColorPlanksBlock> planks) {
        return REGISTRATE.object(id).block(prop -> new ColorSlabBlock(planks, prop))
                .blockstate((ctx, provider) -> provider.slabBlock(ctx.getEntry(),
                        provider.models().getExistingFile(ColorMod.getLocation("block/color_slab")),
                        provider.models().getExistingFile(ColorMod.getLocation("block/color_slab_top")),
                        provider.models().getExistingFile(ColorMod.getLocation("block/color_planks")))
                ).defaultLoot().defaultLang().recipe((ctx, provider) -> provider.slab(DataIngredient.items(planks.get()), ctx::getEntry, "wooden_slab", false))
                .initialProperties(Material.WOOD, MaterialColor.WOOD).properties(PLANKS_PROPS)
                .item().model((ctx, provider) -> provider.withExistingParent(ctx.getName(), ColorMod.getLocation("block/color_slab"))).build().register();
    }

    private static RegistryEntry<ColorGemOreBlock> registerGemOre(String id, DyeColor color, Supplier<ColorGemItem> gem) {
        return REGISTRATE.object(id).block(prop -> new ColorGemOreBlock(prop, color)).defaultLang().tag(Tags.Blocks.ORES)
                .initialProperties(Material.ROCK, MaterialColor.STONE).properties(prop -> prop.hardnessAndResistance(3.0F, 3.0F))
                .blockstate((ctx, provider) -> provider.simpleBlock(ctx.getEntry(), provider.models().getExistingFile(ColorMod.getLocation("block/color_ore"))))
                .loot((lootTables, block) -> lootTables.registerLootTable(block, LootTable.builder()
                        .addLootPool(LootPool.builder()
                                .addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(block)
                                        .acceptCondition(Alternative.builder(MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))))))))
                                .addEntry(ItemLootEntry.builder(gem.get())
                                        .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))
                                        .acceptFunction(ExplosionDecay.builder()))))
                ).item().model((ctx, provider) -> provider.withExistingParent(ctx.getName(), ColorMod.getLocation("block/color_ore"))).build().register();
    }
}
