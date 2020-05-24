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
public class RedBiome extends ColorBiome {

    public RedBiome(Builder builder) {
        super(builder, DyeColor.RED);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.POPPY.getDefaultState());
        flowers.add(Blocks.RED_TULIP.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.RED_TREE.get().withConfiguration(ColorFeatures.getRedTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.RED_GEM_ORE);
    }
}
