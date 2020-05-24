package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorEntities;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeColor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public abstract class ColorBiome extends Biome implements IHasFeatures {

    private final DyeColor color;

    public ColorBiome(Builder builder, DyeColor color) {
        super(builder.waterColor(color.getColorValue()).waterFogColor(color.getColorValue()));
        this.color = color;
    }

    public abstract void getFlowers(List<BlockState> flowers);

    @Override
    public void addFeatures() {
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addSprings(this);

        List<BlockState> flowers = new ArrayList<>();
        getFlowers(flowers);
        if (flowers.size() > 0) {
            WeightedBlockStateProvider provider = new WeightedBlockStateProvider();
            flowers.forEach(state -> provider.addWeightedBlockstate(state, 1));
            final BlockClusterFeatureConfig config = new BlockClusterFeatureConfig.Builder(provider, new SimpleBlockPlacer()).tries(32 * flowers.size()).build();
            this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(config).withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(2))));
        }

        ColorFeatures.addColorGrass(this, ColorFeatures.getColorGrassConfig());
        ColorFeatures.addColorTallGrass(this, ColorFeatures.getTallColorGrassConfig());

        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 3, 5));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(ColorEntities.COLOR_MONSTER.get(), 5, 2, 5));
    }

    @Override
    public int getSkyColor() {
        return this.color.getColorValue();
    }

    @Override
    public int getGrassColor(double posX, double posZ) {
        return this.color.getColorValue();
    }

    @Override
    public int getFoliageColor() {
        return this.color.getColorValue();
    }

    public DyeColor getColor() {
        return color;
    }
}
