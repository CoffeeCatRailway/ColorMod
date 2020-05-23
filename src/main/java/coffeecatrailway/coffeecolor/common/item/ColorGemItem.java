package coffeecatrailway.coffeecolor.common.item;

import coffeecatrailway.coffeecolor.common.IHasColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 23/05/2020
 */
public class ColorGemItem extends Item implements IHasColor {

    private DyeColor color;

    public ColorGemItem(Properties properties, DyeColor color) {
        super(properties);
        this.color = color;
    }

    @Override
    public int getColor(ItemStack stack, int tintindex) {
        return color.getColorValue();
    }
}
