package coffeecatrailway.coffeecolor.common.block;

import coffeecatrailway.coffeecolor.common.IHasColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 13/05/2020
 */
public class ColorStairsBlock extends StairsBlock implements IHasColor {

    private DyeColor color;

    public ColorStairsBlock(Supplier<? extends IHasColor> base, Properties properties) {
        super(((Block) base.get())::getDefaultState, properties);
        this.color = this.getColorByValue(base.get().getColor());
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
