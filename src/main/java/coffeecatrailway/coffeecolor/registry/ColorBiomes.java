package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.biome.ColorBiome;
import coffeecatrailway.coffeecolor.common.biome.ColorRiverBiome;
import coffeecatrailway.coffeecolor.common.biome.IHasFeatures;
import coffeecatrailway.coffeecolor.common.dimension.ColorDimension;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.item.DyeColor;
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
    public static final float RIVER_DEPTH = -1.5f;
    
    // WHITE
    public static final RegistryEntry<ColorBiome> WHITE_PLAINS = REGISTRATE.biome("white_plains", prop -> new ColorBiome(prop, DyeColor.WHITE.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // ORANGE
    public static final RegistryEntry<ColorBiome> ORANGE_PLAINS = REGISTRATE.biome("orange_plains", prop -> new ColorBiome(prop, DyeColor.ORANGE.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // MAGENTA
    public static final RegistryEntry<ColorBiome> MAGENTA_PLAINS = REGISTRATE.biome("magenta_plains", prop -> new ColorBiome(prop, DyeColor.MAGENTA.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // LIGHT_BLUE
    public static final RegistryEntry<ColorBiome> LIGHT_BLUE_PLAINS = REGISTRATE.biome("light_blue_plains", prop -> new ColorBiome(prop, DyeColor.LIGHT_BLUE.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // YELLOW
    public static final RegistryEntry<ColorBiome> YELLOW_PLAINS = REGISTRATE.biome("yellow_plains", prop -> new ColorBiome(prop, DyeColor.YELLOW.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // LIME
    public static final RegistryEntry<ColorBiome> LIME_PLAINS = REGISTRATE.biome("lime_plains", prop -> new ColorBiome(prop, DyeColor.LIME.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // PINK
    public static final RegistryEntry<ColorBiome> PINK_PLAINS = REGISTRATE.biome("pink_plains", prop -> new ColorBiome(prop, DyeColor.PINK.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // GRAY
    public static final RegistryEntry<ColorBiome> GRAY_PLAINS = REGISTRATE.biome("gray_plains", prop -> new ColorBiome(prop, DyeColor.GRAY.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // LIGHT_GRAY
    public static final RegistryEntry<ColorBiome> LIGHT_GRAY_PLAINS = REGISTRATE.biome("light_gray_plains", prop -> new ColorBiome(prop, DyeColor.LIGHT_GRAY.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // CYAN
    public static final RegistryEntry<ColorBiome> CYAN_PLAINS = REGISTRATE.biome("cyan_plains", prop -> new ColorBiome(prop, DyeColor.CYAN.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // PURPLE
    public static final RegistryEntry<ColorBiome> PURPLE_PLAINS = REGISTRATE.biome("purple_plains", prop -> new ColorBiome(prop, DyeColor.PURPLE.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // BLUE
    public static final RegistryEntry<ColorBiome> BLUE_PLAINS = REGISTRATE.biome("blue_plains", prop -> new ColorBiome(prop, DyeColor.BLUE.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // BROWN
    public static final RegistryEntry<ColorBiome> BROWN_PLAINS = REGISTRATE.biome("brown_plains", prop -> new ColorBiome(prop, DyeColor.BROWN.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // GREEN
    public static final RegistryEntry<ColorBiome> GREEN_PLAINS = REGISTRATE.biome("green_plains", prop -> new ColorBiome(prop, DyeColor.GREEN.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // RED
    public static final RegistryEntry<ColorBiome> RED_PLAINS = REGISTRATE.biome("red_plains", prop -> new ColorBiome(prop, DyeColor.RED.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // BLACK
    public static final RegistryEntry<ColorBiome> BLACK_PLAINS = REGISTRATE.biome("black_plains", prop -> new ColorBiome(prop, DyeColor.BLACK.getColorValue()))
            .defaultPrecipitation().defaultParent().category(Biome.Category.PLAINS).depth(PLAINS_DEPTH).scale(0.035f).temperature(0.5f).downfall(0.4f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_BIOME_SURFACE).register();

    // River
    public static final RegistryEntry<ColorRiverBiome> COLOR_RIVER = REGISTRATE.biome("color_river", ColorRiverBiome::new)
            .precipitation(Biome.RainType.NONE).defaultParent().category(Biome.Category.RIVER).depth(RIVER_DEPTH).scale(0.0f).temperature(0.66f).downfall(0.0f)
            .surfaceBuilder(SurfaceBuilder.DEFAULT, COLOR_RIVER_SURFACE).register();

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(WHITE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.COLD);

        BiomeDictionary.addTypes(ORANGE_PLAINS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HOT);

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
    }
}
