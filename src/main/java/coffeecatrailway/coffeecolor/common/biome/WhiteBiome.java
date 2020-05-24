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
public class WhiteBiome extends ColorBiome {

    public WhiteBiome(Builder builder) {
        super(builder, DyeColor.WHITE);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.WHITE_TULIP.getDefaultState());
        flowers.add(Blocks.OXEYE_DAISY.getDefaultState());
        flowers.add(Blocks.LILY_OF_THE_VALLEY.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.WHITE_TREE.get().withConfiguration(ColorFeatures.getWhiteTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.WHITE_GEM_ORE);
    }
}
