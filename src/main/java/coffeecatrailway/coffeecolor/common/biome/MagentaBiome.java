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
public class MagentaBiome extends ColorBiome {

    public MagentaBiome(Builder builder) {
        super(builder, DyeColor.MAGENTA);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.ALLIUM.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTrees(this, ColorFeatures.MAGENTA_TREE.get().withConfiguration(ColorFeatures.getMagentaTreeConfig()), ColorFeatures.PURPLE_TREE.get().withConfiguration(ColorFeatures.getPurpleTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.MAGENTA_GEM_ORE);
        ColorFeatures.addColorGemOre(this, ColorBlocks.PURPLE_GEM_ORE);
    }
}
