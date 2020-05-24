package coffeecatrailway.coffeecolor.common.biome;

import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;

import java.util.List;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorRiverBiome extends ColorBiome {

    public ColorRiverBiome(Builder builder) {
        super(builder, DyeColor.CYAN);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
    }
}
