package coffeecatrailway.coffeecolor;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * @author CoffeeCatRailway
 * Created: 25/05/2020
 */
public class ColorConfig {

    private static final String configArtifact = ColorMod.MOD_ID + ".artifact.";

    public ForgeConfigSpec.BooleanValue canWearMultiple;
    public ForgeConfigSpec.BooleanValue canRightClickEquip;

    public ColorConfig(ForgeConfigSpec.Builder builder) {
        this.canWearMultiple = builder.comment("If true, you can wear multiple color artifacts at once").define(configArtifact + "canWearMultiple", false);
        this.canRightClickEquip = builder.comment("If true, you can press the right-mouse button to equip an artifact").define(configArtifact + "canRightClickEquip", true);
    }
}
