package coffeecatrailway.coffeecolor.common.biome;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.DyeColor;

import java.util.List;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class PinkBiome extends ColorBiome {

    public PinkBiome(Builder builder) {
        super(builder, DyeColor.PINK);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.PINK_TULIP.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTrees(this, ColorFeatures.PINK_TREE.get().withConfiguration(ColorFeatures.getPinkTreeConfig()), ColorFeatures.MAGENTA_TREE.get().withConfiguration(ColorFeatures.getMagentaTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.PINK_GEM_ORE);
        ColorFeatures.addColorGemOre(this, ColorBlocks.MAGENTA_GEM_ORE);
    }
}
