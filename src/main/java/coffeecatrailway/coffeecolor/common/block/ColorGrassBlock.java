package coffeecatrailway.coffeecolor.common.block;

import coffeecatrailway.coffeecolor.common.biome.ColorBiome;
import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorGrassBlock extends Block implements IGrowable {

    public ColorGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.up()).isAir(world, pos);
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = ColorBlocks.COLOR_GRASS.get().getDefaultState();

        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true) {
                if (j >= i / 16) {
                    BlockState blockstate2 = world.getBlockState(blockpos1);
                    if (blockstate2.getBlock() == blockstate.getBlock() && rand.nextInt(10) == 0)
                        ((IGrowable) blockstate.getBlock()).grow(world, rand, blockpos1, blockstate2);

                    if (!blockstate2.isAir())
                        break;

                    BlockState blockstate1 = blockstate;
                    Biome biome = world.getBiome(blockpos1);
                    if (rand.nextInt(8) == 0 && biome instanceof ColorBiome) {
                        List<BlockState> flowers = new ArrayList<>();
                        ((ColorBiome) biome).getFlowers(flowers);
                        if (flowers.isEmpty())
                            break;
                        blockstate1 = flowers.get(rand.nextInt(flowers.size()));
                    }

                    if (blockstate1.isValidPosition(world, blockpos1))
                        world.setBlockState(blockpos1, blockstate1, 3);
                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (world.getBlockState(blockpos1.down()).getBlock() != this || world.getBlockState(blockpos1).isCollisionShapeOpaque(world, blockpos1))
                    break;
                j++;
            }
        }
    }

    private static boolean hasLight(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = world.getBlockState(blockpos);
        int i = LightEngine.func_215613_a(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(world, blockpos));
        return i < world.getMaxLightLevel();
    }

    private static boolean canSpread(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return hasLight(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (!worldIn.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (!hasLight(state, worldIn, pos)) {
                worldIn.setBlockState(pos, ColorBlocks.COLOR_DIRT.get().getDefaultState());
            } else {
                if (worldIn.getLight(pos.up()) >= 9) {
                    BlockState blockstate = this.getDefaultState();

                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (worldIn.getBlockState(blockpos).getBlock() == ColorBlocks.COLOR_DIRT.get() && canSpread(blockstate, worldIn, blockpos))
                            worldIn.setBlockState(blockpos, blockstate);
                    }
                }
            }
        }
    }
}
