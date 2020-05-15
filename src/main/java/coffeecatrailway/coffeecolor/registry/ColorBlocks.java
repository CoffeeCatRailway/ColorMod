package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.block.*;
import coffeecatrailway.coffeecolor.registrate.ColorRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
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

    public static void load() {
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
}
