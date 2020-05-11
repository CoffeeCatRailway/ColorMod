package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.common.item.MagicColorDye;
import com.tterrag.registrate.util.RegistryEntry;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorItems {

    public static final RegistryEntry<MagicColorDye> MAGIC_COLOR_DYE = REGISTRATE.item("magic_color_dye", MagicColorDye::new).properties(prop -> prop.maxStackSize(1)).register();

    public static void load() {
    }
}
