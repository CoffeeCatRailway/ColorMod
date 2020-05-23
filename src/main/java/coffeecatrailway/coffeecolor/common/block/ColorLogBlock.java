package coffeecatrailway.coffeecolor.common.block;

import coffeecatrailway.coffeecolor.common.IHasColor;
import net.minecraft.block.LogBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 13/05/2020
 */
public class ColorLogBlock extends LogBlock implements IHasColor {

    private DyeColor color;

    public ColorLogBlock(DyeColor color, Properties properties) {
        super(color.getMapColor(), properties);
        this.color = color;
    }

    @Override
    public int getColor(ItemStack stack, int tintindex) {
        return this.color.getColorValue();
    }
}
