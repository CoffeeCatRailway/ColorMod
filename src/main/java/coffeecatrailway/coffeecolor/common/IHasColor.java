package coffeecatrailway.coffeecolor.common;

import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 13/05/2020
 */
public interface IHasColor {

    int getColor(ItemStack stack, int tintindex);

    default int getColor() {
        return getColor(null, 0);
    }

    default DyeColor getColorByValue(int color) {
        for (DyeColor dye : DyeColor.values())
            if (dye.getColorValue() == color)
                return dye;
        return DyeColor.WHITE;
    }
}
