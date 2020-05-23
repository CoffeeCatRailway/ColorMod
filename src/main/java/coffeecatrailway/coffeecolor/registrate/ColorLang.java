package coffeecatrailway.coffeecolor.registrate;

import coffeecatrailway.coffeecolor.ColorMod;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.client.settings.KeyBinding;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public class ColorLang implements NonNullConsumer<RegistrateLangProvider> {

    public static final ColorLang INSTANCE = new ColorLang();

    @Override
    public void accept(RegistrateLangProvider provider) {
        provider.add(ColorMod.GROUP, "Coffee's Colored Dimension");
        provider.add(ColorMod.ACTIVE_AMULET.getKeyDescription(), "Activate Color Amulet");
        provider.add(ColorMod.ACTIVE_AMULET.getKeyCategory(), "Coffee's Color Mod");

        ColorMod.LOGGER.info("DataGen: Lang");
    }
}
