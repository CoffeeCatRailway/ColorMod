package coffeecatrailway.coffeecolor.common.biome.feature.tree;

import coffeecatrailway.coffeecolor.common.biome.feature.ColorTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.BigTree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 16/05/2020
 */
public abstract class ColorTree extends BigTree {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean b) {
        return null;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<HugeTreeFeatureConfig, ?> getHugeTreeFeature(Random p_225547_1_) {
        return null;
    }

    public abstract ConfiguredFeature<ColorTreeFeatureConfig, ?> createTreeFeature(Random rand);

    @Override
    public boolean func_227017_a_(IWorld world, ChunkGenerator<?> generator, BlockPos pos, BlockState state, Random rand, int xOff, int zOff) {
        ConfiguredFeature<ColorTreeFeatureConfig, ?> feature = createTreeFeature(rand);
        if (feature == null)
            return false;
        else {
            BlockState blockstate = Blocks.AIR.getDefaultState();
            world.setBlockState(pos.add(xOff, 0, zOff), blockstate, 4);
            world.setBlockState(pos.add(xOff + 1, 0, zOff), blockstate, 4);
            world.setBlockState(pos.add(xOff, 0, zOff + 1), blockstate, 4);
            world.setBlockState(pos.add(xOff + 1, 0, zOff + 1), blockstate, 4);
            if (feature.place(world, generator, rand, pos.add(xOff, 0, zOff))) {
                return true;
            } else {
                world.setBlockState(pos.add(xOff, 0, zOff), state, 4);
                world.setBlockState(pos.add(xOff + 1, 0, zOff), state, 4);
                world.setBlockState(pos.add(xOff, 0, zOff + 1), state, 4);
                world.setBlockState(pos.add(xOff + 1, 0, zOff + 1), state, 4);
                return false;
            }
        }
    }
}
