package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.biome.feature.ColorTreeFeatureConfig;
import coffeecatrailway.coffeecolor.common.biome.feature.tree.ColorTreeFeature;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 16/05/2020
 */
public class ColorFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, ColorMod.MOD_ID);

    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> WHITE_TREE = FEATURES.register("white_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeWhite));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> ORANGE_TREE = FEATURES.register("orange_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeOrange));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> MAGENTA_TREE = FEATURES.register("magenta_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeMagenta));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> LIGHT_BLUE_TREE = FEATURES.register("light_blue_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeLightBlue));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> YELLOW_TREE = FEATURES.register("yellow_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeYellow));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> LIME_TREE = FEATURES.register("lime_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeLime));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> PINK_TREE = FEATURES.register("pink_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializePink));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> GRAY_TREE = FEATURES.register("gray_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeGray));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> LIGHT_GRAY_TREE = FEATURES.register("light_gray_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeLightGray));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> CYAN_TREE = FEATURES.register("cyan_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeCyan));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> PURPLE_TREE = FEATURES.register("purple_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializePurple));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> BLUE_TREE = FEATURES.register("blue_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeBlue));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> BROWN_TREE = FEATURES.register("brown_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeBrown));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> GREEN_TREE = FEATURES.register("green_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeGreen));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> RED_TREE = FEATURES.register("red_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeRed));
    public static final RegistryObject<Feature<ColorTreeFeatureConfig>> BLACK_TREE = FEATURES.register("black_feature", () -> new ColorTreeFeature(ColorTreeFeatureConfig::deserializeBlack));

    public static ColorTreeFeatureConfig getWhiteTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.WHITE_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.WHITE_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.WHITE_SAPLING.get()).baseHeight(10)).build();
    }

    public static ColorTreeFeatureConfig getOrangeTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.ORANGE_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.ORANGE_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.ORANGE_SAPLING.get()).baseHeight(5)).build();
    }

    public static ColorTreeFeatureConfig getMagentaTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.MAGENTA_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.MAGENTA_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.MAGENTA_SAPLING.get()).baseHeight(8)).build();
    }

    public static ColorTreeFeatureConfig getLightBlueTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.LIGHT_BLUE_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.LIGHT_BLUE_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.LIGHT_BLUE_SAPLING.get()).baseHeight(6)).build();
    }

    public static ColorTreeFeatureConfig getYellowTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.YELLOW_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.YELLOW_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.YELLOW_SAPLING.get()).baseHeight(5)).build();
    }

    public static ColorTreeFeatureConfig getLimeTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.LIME_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.LIME_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.LIME_SAPLING.get()).baseHeight(7)).build();
    }

    public static ColorTreeFeatureConfig getPinkTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.PINK_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.PINK_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.PINK_SAPLING.get()).baseHeight(8)).build();
    }

    public static ColorTreeFeatureConfig getGrayTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.GRAY_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.GRAY_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.GRAY_SAPLING.get()).baseHeight(10)).build();
    }

    public static ColorTreeFeatureConfig getLightGrayTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.LIGHT_GRAY_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.LIGHT_GRAY_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.LIGHT_GRAY_SAPLING.get()).baseHeight(10)).build();
    }

    public static ColorTreeFeatureConfig getCyanTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.CYAN_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.CYAN_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.CYAN_SAPLING.get()).baseHeight(6)).build();
    }

    public static ColorTreeFeatureConfig getPurpleTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.PURPLE_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.PURPLE_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.PURPLE_SAPLING.get()).baseHeight(8)).build();
    }

    public static ColorTreeFeatureConfig getBlueTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.BLUE_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.BLUE_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.BLUE_SAPLING.get()).baseHeight(6)).build();
    }

    public static ColorTreeFeatureConfig getBrownTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.BROWN_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.BROWN_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.BROWN_SAPLING.get()).baseHeight(8)).build();
    }

    public static ColorTreeFeatureConfig getGreenTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.GREEN_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.GREEN_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.GREEN_SAPLING.get()).baseHeight(7)).build();
    }

    public static ColorTreeFeatureConfig getRedTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.RED_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.RED_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.RED_SAPLING.get()).baseHeight(5)).build();
    }

    public static ColorTreeFeatureConfig getBlackTreeConfig() {
        return (new ColorTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.BLACK_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(ColorBlocks.BLACK_LEAVES.get().getDefaultState())).setSapling(ColorBlocks.BLACK_SAPLING.get()).baseHeight(10)).build();
    }

    public static BlockClusterFeatureConfig getColorGrassConfig() {
        return new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().addWeightedBlockstate(ColorBlocks.COLOR_GRASS.get().getDefaultState(), 3), new SimpleBlockPlacer()).tries(32).build();
    }

    public static BlockClusterFeatureConfig getTallColorGrassConfig() {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ColorBlocks.TALL_COLOR_GRASS.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
    }

    public static void addColorTree(Biome biome, ConfiguredFeature<? extends ColorTreeFeatureConfig, ?> tree, int count) {
        addColorTrees(biome, tree, tree, count);
    }

    public static void addColorTrees(Biome biome, ConfiguredFeature<? extends ColorTreeFeatureConfig, ?> first, ConfiguredFeature<? extends ColorTreeFeatureConfig, ?> second, int count) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(second.withChance(0.15F)), first)).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, 0.1F, 1))));
    }

    public static void addColorGrass(Biome biome, BlockClusterFeatureConfig config) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(25))));
    }

    public static void addColorTallGrass(Biome biome, BlockClusterFeatureConfig config) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(7))));
    }
}
