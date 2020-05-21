package coffeecatrailway.coffeecolor.common.block;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IShearable;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class ColorTallGrassBlock extends BushBlock implements IGrowable, IShearable {

    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public ColorTallGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        if (ColorBlocks.TALL_COLOR_GRASS.get().getDefaultState().isValidPosition(world, pos) && world.isAirBlock(pos.up()))
            ColorBlocks.TALL_COLOR_GRASS.get().placeAt(world, pos, 2);
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XYZ;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos.down());
        Block block = blockstate.getBlock();
        return ColorSaplingBlock.isSoil(blockstate) || super.isValidGround(state, world, pos) || block == Blocks.FARMLAND;
    }
}
