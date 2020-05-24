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
public class BlueBiome extends ColorBiome {

    public BlueBiome(Builder builder) {
        super(builder, DyeColor.BLUE);
    }

    @Override
    public void getFlowers(List<BlockState> flowers) {
        flowers.add(Blocks.CORNFLOWER.getDefaultState());
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        if (this.category == Category.FOREST)
            ColorFeatures.addColorTree(this, ColorFeatures.BLUE_TREE.get().withConfiguration(ColorFeatures.getBlueTreeConfig()), 8);
        ColorFeatures.addColorGemOre(this, ColorBlocks.BLUE_GEM_ORE);
    }
}
