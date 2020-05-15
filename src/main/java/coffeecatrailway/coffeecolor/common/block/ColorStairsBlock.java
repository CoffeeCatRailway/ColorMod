package coffeecatrailway.coffeecolor.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
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
        this.color = base.get().getColor();
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }

    @Override
    public MaterialColor getMaterialColor(BlockState state, IBlockReader world, BlockPos pos) {
        return this.color.getMapColor();
    }
}
