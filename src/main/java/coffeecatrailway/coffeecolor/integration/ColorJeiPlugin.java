package coffeecatrailway.coffeecolor.integration;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.item.ColorAmuletItem;
import coffeecatrailway.coffeecolor.registry.ColorItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.util.ResourceLocation;

/**
 * @author CoffeeCatRailway
 * Created: 23/05/2020
 */
@JeiPlugin
public class ColorJeiPlugin implements IModPlugin {

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ColorItems.COLOR_AMULET.get(), stack -> {
            if (!stack.hasTag())
                return ISubtypeInterpreter.NONE;
            return String.valueOf(stack.getTag().getByte(ColorAmuletItem.TAG_COLOR));
        });
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ColorMod.getLocation(ColorMod.MOD_ID);
    }
}
