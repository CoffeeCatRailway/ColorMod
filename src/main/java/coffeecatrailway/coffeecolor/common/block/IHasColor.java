package coffeecatrailway.coffeecolor.common.block;

import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;

/**
 * @author CoffeeCatRailway
 * Created: 13/05/2020
 */
public interface IHasColor {

    DyeColor getColor();

    static MaterialColor getMaterialColorFromDyeColor(DyeColor color) {
        switch (color) {
            default:
            case WHITE:
                return MaterialColor.WHITE_TERRACOTTA;
            case ORANGE:
                return MaterialColor.ORANGE_TERRACOTTA;
            case MAGENTA:
                return MaterialColor.MAGENTA;
            case LIGHT_BLUE:
                return MaterialColor.LIGHT_BLUE;
            case YELLOW:
                return MaterialColor.YELLOW;
            case LIME:
                return MaterialColor.LIME;
            case PINK:
                return MaterialColor.PINK;
            case GRAY:
                return MaterialColor.GRAY;
            case LIGHT_GRAY:
                return MaterialColor.LIGHT_GRAY;
            case CYAN:
                return MaterialColor.CYAN;
            case PURPLE:
                return MaterialColor.PURPLE;
            case BLUE:
                return MaterialColor.BLUE;
            case BROWN:
                return MaterialColor.BROWN;
            case GREEN:
                return MaterialColor.GREEN;
            case RED:
                return MaterialColor.RED;
            case BLACK:
                return MaterialColor.BLACK;
        }
    }
}
