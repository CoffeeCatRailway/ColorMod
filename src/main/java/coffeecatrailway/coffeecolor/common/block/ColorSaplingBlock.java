package coffeecatrailway.coffeecolor.common.block;

import coffeecatrailway.coffeecolor.common.IHasColor;
import coffeecatrailway.coffeecolor.common.biome.feature.tree.ColorTree;
import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

/**
 * @author CoffeeCatRailway
 * Created: 16/05/2020
 */
public class ColorSaplingBlock extends SaplingBlock implements IHasColor {

    private DyeColor color;

    public ColorSaplingBlock(DyeColor color, ColorTree tree, Properties properties) {
        super(tree, properties);
        this.color = color;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        return isSoil(world.getBlockState(pos.down()));
    }

    public static boolean isSoil(BlockState state) {
        return state.getBlock() == ColorBlocks.COLOR_GRASS_BLOCK.get() || state.getBlock() == ColorBlocks.COLOR_DIRT.get();
    }

    @Override
    public int getColor(ItemStack stack, int tintindex) {
        return this.color.getColorValue();
    }

    @Override
    public MaterialColor getMaterialColor(BlockState state, IBlockReader world, BlockPos pos) {
        return this.color.getMapColor();
    }
}
