package coffeecatrailway.coffeecolor.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @author CoffeeCatRailway
 * Created: 15/05/2020
 */
public class ColorLeavesBlock extends LeavesBlock implements IHasColor {

    private DyeColor color;

    public ColorLeavesBlock(DyeColor color, Properties properties) {
        super(properties);
        this.color = color;
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