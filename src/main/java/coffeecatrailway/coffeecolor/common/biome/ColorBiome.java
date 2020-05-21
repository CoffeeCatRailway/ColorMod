package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorEntities;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeColor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorBiome extends Biome implements IHasFeatures {

    private final DyeColor color;

    public ColorBiome(Builder builder, DyeColor color) {
        super(builder.waterColor(color.getColorValue()).waterFogColor(color.getColorValue()));
        this.color = color;
    }

    @Override
    public void addFeatures() {
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addSprings(this);

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
