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
public class OrangeBiome extends ColorBiome {

    public OrangeBiome(Builder builder) {
        super(builder, DyeColor.ORANGE);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.ORANGE_TULIP.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTrees(this, ColorFeatures.ORANGE_TREE.get().withConfiguration(ColorFeatures.getOrangeTreeConfig()), ColorFeatures.RED_TREE.get().withConfiguration(ColorFeatures.getRedTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.ORANGE_GEM_ORE);
        ColorFeatures.addColorGemOre(this, ColorBlocks.RED_GEM_ORE);
    }
}
