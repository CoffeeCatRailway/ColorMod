package coffeecatrailway.coffeecolor.common.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorBiome extends Biome implements IHasFeatures {

    private final int color;

    public ColorBiome(Builder builder, int color) {
        super(builder.waterColor(color).waterFogColor(color));
        this.color = color;
    }

    @Override
    public void addFeatures() {
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addSprings(this);
    }

    @Override
    public int getSkyColor() {
        return this.color;
    }

    @Override
    public int getGrassColor(double posX, double posZ) {
        return this.color;
    }

    @Override
    public int getFoliageColor() {
        return this.color;
    }
}
