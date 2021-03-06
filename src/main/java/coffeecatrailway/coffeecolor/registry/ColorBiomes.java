package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.biome.*;
import coffeecatrailway.coffeecolor.common.dimension.ColorDimension;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorBiomes {

    /* Dimension */
    public static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, ColorMod.MOD_ID);

    public static final RegistryObject<ModDimension> COLOR_DIMENSION = DIMENSIONS.register("color_dimension", () -> ModDimension.withFactory(ColorDimension::new));
    public static DimensionType COLOR_DIMENSION_TYPE;

    /* Biomes */
    private static final NonNullSupplier<SurfaceBuilderConfig> COLOR_BIOME_SURFACE = () -> new SurfaceBuilderConfig(ColorBlocks.COLOR_GRASS_BLOCK.get().getDefaultState(), ColorBlocks.COLOR_DIRT.get().getDefaultState(), ColorBlocks.COLOR_DIRT.get().getDefaultState());
    private static final NonNullSupplier<SurfaceBuilderConfig> COLOR_RIVER_SURFACE = () -> new SurfaceBuilderConfig(ColorBlocks.COLOR_DIRT.get().getDefaultState(), ColorBlocks.COLOR_DIRT.get().getDefaultState(), ColorBlocks.COLOR_DIRT.get().getDefaultState());

    public static final float PLAINS_DEPTH = -0.125f;
    public static final float FOREST_DEPTH = -0.145f;
    public static final float FOREST_HILLS_DEPTH = -0.5f;

    public static final float PLAINS_DOWNFALL = 0.75f;
    public static final float FOREST_DOWNFALL = 0.85f;
    public static final float FOREST_HILLS_DOWNFALL = 0.4f;

    public static final float RIVER_DEPTH = -1.5f;

    // WHITE
    public static final RegistryEntry<WhiteBiome> WHITE_PLAINS = REGISTRATE.biome("white_plains", WhiteBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<WhiteBiome> WHITE_FOREST = REGISTRATE.biome("white_forest", WhiteBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<WhiteBiome> WHITE_FOREST_HILLS = REGISTRATE.biome("white_forest_hills", WhiteBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // ORANGE
    public static final RegistryEntry<OrangeBiome> ORANGE_PLAINS = REGISTRATE.biome("orange_plains", OrangeBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<OrangeBiome> ORANGE_FOREST = REGISTRATE.biome("orange_forest", OrangeBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<OrangeBiome> ORANGE_FOREST_HILLS = REGISTRATE.biome("orange_forest_hills", OrangeBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // MAGENTA
    public static final RegistryEntry<MagentaBiome> MAGENTA_PLAINS = REGISTRATE.biome("magenta_plains", MagentaBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<MagentaBiome> MAGENTA_FOREST = REGISTRATE.biome("magenta_forest", MagentaBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<MagentaBiome> MAGENTA_FOREST_HILLS = REGISTRATE.biome("magenta_forest_hills", MagentaBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // LIGHT_BLUE
    public static final RegistryEntry<LightBlueBiome> LIGHT_BLUE_PLAINS = REGISTRATE.biome("light_blue_plains", LightBlueBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<LightBlueBiome> LIGHT_BLUE_FOREST = REGISTRATE.biome("light_blue_forest", LightBlueBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<LightBlueBiome> LIGHT_BLUE_FOREST_HILLS = REGISTRATE.biome("light_blue_hills", LightBlueBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // YELLOW
    public static final RegistryEntry<YellowBiome> YELLOW_PLAINS = REGISTRATE.biome("yellow_plains", YellowBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<YellowBiome> YELLOW_FOREST = REGISTRATE.biome("yellow_forest", YellowBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<YellowBiome> YELLOW_FOREST_HILLS = REGISTRATE.biome("yellow_forest_hills", YellowBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // LIME
    public static final RegistryEntry<LimeBiome> LIME_PLAINS = REGISTRATE.biome("lime_plains", LimeBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<LimeBiome> LIME_FOREST = REGISTRATE.biome("lime_forest", LimeBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<LimeBiome> LIME_FOREST_HILLS = REGISTRATE.biome("lime_forest_hills", LimeBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // PINK
    public static final RegistryEntry<PinkBiome> PINK_PLAINS = REGISTRATE.biome("pink_plains", PinkBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<PinkBiome> PINK_FOREST = REGISTRATE.biome("pink_forest", PinkBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<PinkBiome> PINK_FOREST_HILLS = REGISTRATE.biome("pink_forest_hills", PinkBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // GRAY
    public static final RegistryEntry<GrayBiome> GRAY_PLAINS = REGISTRATE.biome("gray_plains", GrayBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<GrayBiome> GRAY_FOREST = REGISTRATE.biome("gray_forest", GrayBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<GrayBiome> GRAY_FOREST_HILLS = REGISTRATE.biome("gray_forest_hills", GrayBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // LIGHT_GRAY
    public static final RegistryEntry<LightGrayBiome> LIGHT_GRAY_PLAINS = REGISTRATE.biome("light_gray_plains", LightGrayBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<LightGrayBiome> LIGHT_GRAY_FOREST = REGISTRATE.biome("light_gray_forest", LightGrayBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<LightGrayBiome> LIGHT_GRAY_FOREST_HILLS = REGISTRATE.biome("light_gray_forest_hills", LightGrayBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // CYAN
    public static final RegistryEntry<CyanBiome> CYAN_PLAINS = REGISTRATE.biome("cyan_plains", CyanBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<CyanBiome> CYAN_FOREST = REGISTRATE.biome("cyan_forest", CyanBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<CyanBiome> CYAN_FOREST_HILLS = REGISTRATE.biome("cyan_forest_hills", CyanBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // PURPLE
    public static final RegistryEntry<PurpleBiome> PURPLE_PLAINS = REGISTRATE.biome("purple_plains", PurpleBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<PurpleBiome> PURPLE_FOREST = REGISTRATE.biome("purple_forest", PurpleBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<PurpleBiome> PURPLE_FOREST_HILLS = REGISTRATE.biome("purple_forest_hills", PurpleBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // BLUE
    public static final RegistryEntry<BlueBiome> BLUE_PLAINS = REGISTRATE.biome("blue_plains", BlueBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<BlueBiome> BLUE_FOREST = REGISTRATE.biome("blue_forest", BlueBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<BlueBiome> BLUE_FOREST_HILLS = REGISTRATE.biome("blue_forest_hills", BlueBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // BROWN
    public static final RegistryEntry<BrownBiome> BROWN_PLAINS = REGISTRATE.biome("brown_plains", BrownBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<BrownBiome> BROWN_FOREST = REGISTRATE.biome("brown_forest", BrownBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<BrownBiome> BROWN_FOREST_HILLS = REGISTRATE.biome("brown_forest_hills", BrownBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // GREEN
    public static final RegistryEntry<GreenBiome> GREEN_PLAINS = REGISTRATE.biome("green_plains", GreenBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<GreenBiome> GREEN_FOREST = REGISTRATE.biome("green_forest", GreenBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<GreenBiome> GREEN_FOREST_HILLS = REGISTRATE.biome("green_forest_hills", GreenBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // RED
    public static final RegistryEntry<RedBiome> RED_PLAINS = REGISTRATE.biome("red_plains", RedBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<RedBiome> RED_FOREST = REGISTRATE.biome("red_forest", RedBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<RedBiome> RED_FOREST_HILLS = REGISTRATE.biome("red_forest_hills", RedBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // BLACK
    public static final RegistryEntry<BlackBiome> BLACK_PLAINS = REGISTRATE.biome("black_plains", BlackBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(.035f).temperature(.5f).downfall(PLAINS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<BlackBiome> BLACK_FOREST = REGISTRATE.biome("black_forest", BlackBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_DEPTH).scale(.05f).temperature(.5f).downfall(FOREST_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();
    public static final RegistryEntry<BlackBiome> BLACK_FOREST_HILLS = REGISTRATE.biome("black_forest_hills", BlackBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.RAIN).parent(null).category(Biome.Category.FOREST).depth(FOREST_HILLS_DEPTH).scale(.03f).temperature(.6f).downfall(FOREST_HILLS_DOWNFALL)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE.get())).defaultLang().register();

    // River
    public static final RegistryEntry<ColorRiverBiome> COLOR_RIVER = REGISTRATE.biome("color_river", ColorRiverBiome::new)
            .properties(prop -> prop.precipitation(Biome.RainType.NONE).parent(null).category(Biome.Category.RIVER).depth(RIVER_DEPTH).scale(0f).temperature(.66f).downfall(0f)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_RIVER_SURFACE.get())).defaultLang().register();

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(WHITE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(WHITE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.COLD);
        BiomeDictionary.addTypes(WHITE_FOREST_HILLS.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(ORANGE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);
        BiomeDictionary.addTypes(ORANGE_FOREST.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT);
        BiomeDictionary.addTypes(ORANGE_FOREST_HILLS.get(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.HOT);

        BiomeDictionary.addTypes(MAGENTA_PLAINS.get(), BiomeDictionary.Type.PLAINS);

        BiomeDictionary.addTypes(LIGHT_BLUE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(YELLOW_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);

        BiomeDictionary.addTypes(LIME_PLAINS.get(), BiomeDictionary.Type.PLAINS);

        BiomeDictionary.addTypes(PINK_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);

        BiomeDictionary.addTypes(GRAY_PLAINS.get(), BiomeDictionary.Type.PLAINS);

        BiomeDictionary.addTypes(LIGHT_GRAY_PLAINS.get(), BiomeDictionary.Type.PLAINS);

        BiomeDictionary.addTypes(CYAN_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(PURPLE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(BLUE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(BROWN_PLAINS.get(), BiomeDictionary.Type.PLAINS);

        BiomeDictionary.addTypes(GREEN_PLAINS.get(), BiomeDictionary.Type.PLAINS);

        BiomeDictionary.addTypes(RED_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);

        BiomeDictionary.addTypes(BLACK_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);

        BiomeDictionary.addTypes(COLOR_RIVER.get(), BiomeDictionary.Type.RIVER);
    }

    public static void addBiomeFeatures() {
        for (Biome biome : ForgeRegistries.BIOMES.getValues())
            if (biome instanceof IHasFeatures)
                ((IHasFeatures) biome).addFeatures();
    }

    public static void load() {
        ColorMod.LOGGER.info("Register biomes");
    }
}
