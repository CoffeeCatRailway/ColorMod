package coffeecatrailway.coffeecolor.common.block;

import net.minecraft.block.LogBlock;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 13/05/2020
 */
public class ColorLogBlock extends LogBlock implements IHasColor {

    private DyeColor color;

    public ColorLogBlock(DyeColor color, Properties properties) {
        super(IHasColor.getMaterialColorFromDyeColor(color), properties);
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }
}
