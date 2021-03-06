package coffeecatrailway.coffeecolor.common.biome.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public enum ColorRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer {

    INSTANCE;

    @Override
    public int apply(INoiseRandom rand, IArea area1, IArea area2, int val1, int val2) {
        int i = area1.getValue(this.getOffsetX(val1), this.getOffsetZ(val2));
        int j = area2.getValue(this.getOffsetX(val1), this.getOffsetZ(val2));
        return j == ColorLayerUtil.RIVER.getAsInt() ? j : i;
    }
}
