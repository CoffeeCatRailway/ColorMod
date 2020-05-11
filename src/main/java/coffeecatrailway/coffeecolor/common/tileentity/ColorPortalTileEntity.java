package coffeecatrailway.coffeecolor.common.tileentity;

import coffeecatrailway.coffeecolor.registry.ColorTileEntities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorPortalTileEntity extends TileEntity {

    public ColorPortalTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public ColorPortalTileEntity() {
        super(ColorTileEntities.COLOR_PORTAL.get());
    }
}
