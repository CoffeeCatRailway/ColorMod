package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.common.item.MagicColorDye;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraftforge.common.Tags;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorItems {

    public static final RegistryEntry<MagicColorDye> MAGIC_COLOR_DYE = REGISTRATE.item("magic_color_dye", MagicColorDye::new).properties(prop -> prop.maxStackSize(1))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx.getEntry(), 1)
                    .addIngredient(Tags.Items.DYES).addIngredient(Tags.Items.DYES).addIngredient(Tags.Items.DYES)
                    .addCriterion("has_dye", provider.hasItem(Tags.Items.DYES)).build(provider))
            .model((ctx, provider) -> provider.generated(ctx::getEntry)).register();

    public static void load() {
    }
}
