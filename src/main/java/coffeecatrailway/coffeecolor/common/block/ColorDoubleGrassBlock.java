package coffeecatrailway.coffeecolor.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

/**
 * @author CoffeeCatRailway
 * Created: 17/05/2020
 */
public class ColorDoubleGrassBlock extends DoublePlantBlock {

    public ColorDoubleGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos.down());
        Block block = blockstate.getBlock();
        return ColorSaplingBlock.isSoil(blockstate) || super.isValidGround(state, world, pos) || block == Blocks.FARMLAND || (block == this && blockstate.get(HALF) == DoubleBlockHalf.LOWER);
    }
}
