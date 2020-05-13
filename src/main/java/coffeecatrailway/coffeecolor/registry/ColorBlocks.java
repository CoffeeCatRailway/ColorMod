package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.block.ColorGrassBlock;
import coffeecatrailway.coffeecolor.common.block.ColorLogBlock;
import coffeecatrailway.coffeecolor.common.block.ColorPortalBlock;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ToolType;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorBlocks {

    public static final RegistryEntry<Block> COLOR_DIRT = REGISTRATE.object("color_dirt").block(Block::new)
            .defaultBlockstate().defaultLoot().defaultLang()
            .initialProperties(Material.EARTH, MaterialColor.DIRT).properties(prop -> prop.hardnessAndResistance(0.5F).sound(SoundType.GROUND)).simpleItem().register();

    public static final RegistryEntry<ColorGrassBlock> COLOR_GRASS_BLOCK = REGISTRATE.object("color_grass_block").block(ColorGrassBlock::new)
            .blockstate(NonNullBiConsumer.noop()).defaultLang()
            .loot((lootTables, block) -> lootTables.registerLootTable(block, RegistrateBlockLootTables.droppingWithSilkTouch(block, COLOR_DIRT.get())))
            .initialProperties(Material.ORGANIC, MaterialColor.GRASS).properties(prop -> prop.tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).simpleItem().register();

    public static final RegistryEntry<ColorPortalBlock> COLOR_PORTAL = REGISTRATE.block("color_portal", ColorPortalBlock::new)
            .blockstate(NonNullBiConsumer.noop()).defaultLang()
            .initialProperties(Material.PORTAL, Material.PORTAL.getColor())
            .properties(prop -> prop.doesNotBlockMovement().harvestTool(ToolType.PICKAXE).hardnessAndResistance(-1.0F, 3000000.0F).lightValue(10).sound(SoundType.GLASS).notSolid())
            .register();

    private static final NonNullBiConsumer<DataGenContext<Block, ColorLogBlock>, RegistrateBlockstateProvider> LOG_STATE = (ctx, provider) -> {
        ModelFile colorLog = provider.models().withExistingParent(ctx.getName(), ColorMod.getLocation("block/color_log"));
        provider.getVariantBuilder(ctx.getEntry())
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(colorLog).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(colorLog).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(colorLog).rotationX(90).rotationY(90).addModel();
    };
    private static final NonNullUnaryOperator<Block.Properties> LOG_PROPS = prop -> prop.hardnessAndResistance(2.0F).sound(SoundType.WOOD);

    public static final RegistryEntry<ColorLogBlock> WHITE_LOG = REGISTRATE.object("white_log").block(prop -> new ColorLogBlock(DyeColor.WHITE, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> ORANGE_LOG = REGISTRATE.object("orange_log").block(prop -> new ColorLogBlock(DyeColor.ORANGE, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> MAGENTA_LOG = REGISTRATE.object("magenta_log").block(prop -> new ColorLogBlock(DyeColor.MAGENTA, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> LIGHT_BLUE_LOG = REGISTRATE.object("light_blue_log").block(prop -> new ColorLogBlock(DyeColor.LIGHT_BLUE, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> YELLOW_LOG = REGISTRATE.object("yellow_log").block(prop -> new ColorLogBlock(DyeColor.YELLOW, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> LIME_LOG = REGISTRATE.object("lime_log").block(prop -> new ColorLogBlock(DyeColor.LIME, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> PINK_LOG = REGISTRATE.object("pink_log").block(prop -> new ColorLogBlock(DyeColor.PINK, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> GRAY_LOG = REGISTRATE.object("gray_log").block(prop -> new ColorLogBlock(DyeColor.GRAY, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> LIGHT_GRAY_LOG = REGISTRATE.object("light_gray_log").block(prop -> new ColorLogBlock(DyeColor.LIGHT_GRAY, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> CYAN_LOG = REGISTRATE.object("cyan_log").block(prop -> new ColorLogBlock(DyeColor.CYAN, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> PURPLE_LOG = REGISTRATE.object("purple_log").block(prop -> new ColorLogBlock(DyeColor.PURPLE, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> BLUE_LOG = REGISTRATE.object("blue_log").block(prop -> new ColorLogBlock(DyeColor.BLUE, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> BROWN_LOG = REGISTRATE.object("brown_log").block(prop -> new ColorLogBlock(DyeColor.BROWN, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> GREEN_LOG = REGISTRATE.object("green_log").block(prop -> new ColorLogBlock(DyeColor.GREEN, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> RED_LOG = REGISTRATE.object("red_log").block(prop -> new ColorLogBlock(DyeColor.RED, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang()
            .initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();
    public static final RegistryEntry<ColorLogBlock> BLACK_LOG = REGISTRATE.object("black_log").block(prop -> new ColorLogBlock(DyeColor.BLACK, prop))
            .blockstate(LOG_STATE).defaultLoot().defaultLang().initialProperties(Material.WOOD, MaterialColor.DIRT).properties(LOG_PROPS).simpleItem().register();

    public static void load() {
    }
}
