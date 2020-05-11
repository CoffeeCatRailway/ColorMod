package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.common.tileentity.ColorPortalTileEntity;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.tileentity.TileEntityType;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorTileEntities {

    public static final RegistryEntry<TileEntityType<ColorPortalTileEntity>> COLOR_PORTAL = REGISTRATE.tileEntity("color_portal", ColorPortalTileEntity::new).validBlock(ColorBlocks.COLOR_PORTAL).register();

    public static void load() {
    }
}
