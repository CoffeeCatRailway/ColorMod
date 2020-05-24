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
public class BlackBiome extends ColorBiome {

    public BlackBiome(Builder builder) {
        super(builder, DyeColor.BLACK);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.WITHER_ROSE.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.BLUE_TREE.get().withConfiguration(ColorFeatures.getBlackTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.BLACK_GEM_ORE);
    }
}
