package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.item.crafting.ColorAmuletRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author CoffeeCatRailway
 * Created: 23/05/2020
 */
public class ColorRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, ColorMod.MOD_ID);

    public static final RegistryObject<SpecialRecipeSerializer<ColorAmuletRecipe>> COLOR_AMULET_SERIALIZER = RECIPE_SERIALIZERS.register("color_amulet", () -> new SpecialRecipeSerializer<>(ColorAmuletRecipe::new));
}
