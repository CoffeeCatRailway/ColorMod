package coffeecatrailway.coffeecolor.common.biome.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public enum ColorRiverLayer implements ICastleTransformer {

    INSTANCE;

    public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
        int i = riverFilter(center);
        return i == riverFilter(east) && i == riverFilter(north) && i == riverFilter(west) && i == riverFilter(south) ? -1 : ColorLayerUtil.RIVER.getAsInt();
    }

    private static int riverFilter(int p_151630_0_) {
        return p_151630_0_ >= 2 ? 2 + (p_151630_0_ & 1) : p_151630_0_;
    }
}
