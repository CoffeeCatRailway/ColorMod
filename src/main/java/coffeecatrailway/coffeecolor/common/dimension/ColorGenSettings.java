package coffeecatrailway.coffeecolor.common.dimension;

import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.gen.GenerationSettings;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorGenSettings extends GenerationSettings {

    public static final int SEA_LEVEL = 63;

    public static ColorGenSettings createDefault() {
        ColorGenSettings genSettings = new ColorGenSettings();
        genSettings.setDefaultBlock(Blocks.STONE.getDefaultState());
        genSettings.setDefaultFluid(Fluids.WATER.getDefaultState().getBlockState());
        return genSettings;
    }

    @Override
    public int getBedrockFloorHeight() {
        return 0;
    }
}
