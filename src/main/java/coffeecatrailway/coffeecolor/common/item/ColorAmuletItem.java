package coffeecatrailway.coffeecolor.common.item;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.client.model.ColorAmuletModel;

/**
 * @author CoffeeCatRailway
 * Created: 24/05/2020
 */
public class ColorAmuletItem extends ColorArtifactItem {

    public ColorAmuletItem(Properties properties) {
        super(properties, ColorMod.getLocation("textures/model/color_amulet.png"), ColorAmuletModel::new);
    }
}
