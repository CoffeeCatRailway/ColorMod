package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.block.ColorGemOreBlock;
import coffeecatrailway.coffeecolor.common.item.ColorAmuletItem;
import coffeecatrailway.coffeecolor.common.item.ColorGemItem;
import coffeecatrailway.coffeecolor.common.item.MagicColorDyeItem;
import coffeecatrailway.coffeecolor.registrate.ColorTags;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorItems {

    public static final RegistryEntry<MagicColorDyeItem> MAGIC_COLOR_DYE = REGISTRATE.item("magic_color_dye", MagicColorDyeItem::new).properties(prop -> prop.maxStackSize(1))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapelessRecipe(ctx.getEntry(), 1)
                    .addIngredient(Tags.Items.DYES).addIngredient(Tags.Items.DYES).addIngredient(Tags.Items.DYES)
                    .addCriterion("has_dye", provider.hasItem(Tags.Items.DYES)).build(provider))
            .model((ctx, provider) -> provider.generated(ctx::getEntry)).register();

    public static final RegistryEntry<ColorAmuletItem> COLOR_AMULET = REGISTRATE.item("color_amulet", ColorAmuletItem::new).properties(prop -> prop.maxStackSize(1))
            .defaultLang().tag(ColorTags.Items.CURIOS_NECKLACE)
            .model((ctx, provider) -> provider.generated(ctx::getEntry, ColorMod.getLocation("item/color_amulet_chain"), ColorMod.getLocation("item/color_amulet_gem"))).register();

    public static final RegistryEntry<ColorGemItem> WHITE_GEM = registerGem("white_gem", DyeColor.WHITE, ColorBlocks.WHITE_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> ORANGE_GEM = registerGem("orange_gem", DyeColor.ORANGE, ColorBlocks.ORANGE_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> MAGENTA_GEM = registerGem("magenta_gem", DyeColor.MAGENTA, ColorBlocks.MAGENTA_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> LIGHT_BLUE_GEM = registerGem("light_blue_gem", DyeColor.LIGHT_BLUE, ColorBlocks.LIGHT_BLUE_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> YELLOW_GEM = registerGem("yellow_gem", DyeColor.YELLOW, ColorBlocks.YELLOW_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> LIME_GEM = registerGem("lime_gem", DyeColor.LIME, ColorBlocks.LIME_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> PINK_GEM = registerGem("pink_gem", DyeColor.PINK, ColorBlocks.PINK_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> GRAY_GEM = registerGem("gray_gem", DyeColor.GRAY, ColorBlocks.GRAY_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> LIGHT_GRAY_GEM = registerGem("light_gray_gem", DyeColor.LIGHT_GRAY, ColorBlocks.LIGHT_GRAY_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> CYAN_GEM = registerGem("cyan_gem", DyeColor.CYAN, ColorBlocks.CYAN_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> PURPLE_GEM = registerGem("purple_gem", DyeColor.PURPLE, ColorBlocks.PURPLE_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> BLUE_GEM = registerGem("blue_gem", DyeColor.BLUE, ColorBlocks.BLUE_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> BROWN_GEM = registerGem("brown_gem", DyeColor.BROWN, ColorBlocks.BROWN_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> GREEN_GEM = registerGem("green_gem", DyeColor.GREEN, ColorBlocks.GREEN_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> RED_GEM = registerGem("red_gem", DyeColor.RED, ColorBlocks.RED_GEM_ORE);
    public static final RegistryEntry<ColorGemItem> BLACK_GEM = registerGem("black_gem", DyeColor.BLACK, ColorBlocks.BLACK_GEM_ORE);

    public static void load() {
        ColorMod.LOGGER.info("Register items");
    }

    private static RegistryEntry<ColorGemItem> registerGem(String id, DyeColor color, Supplier<ColorGemOreBlock> ore) {
        return REGISTRATE.item(id, prop -> new ColorGemItem(prop, color)).defaultLang().tag(ColorTags.Items.COLOR_GEMS)
                .recipe((ctx, provider) -> {
                    provider.blasting(DataIngredient.items(ore.get()), ctx::getEntry, 1.0f);
                    provider.cooking(DataIngredient.items(ore.get()), ctx::getEntry, 1.0f, 200, IRecipeSerializer.SMELTING);
                }).model((ctx, provider) -> provider.generated(ctx::getEntry, ColorMod.getLocation("item/color_gem"))).register();
    }
}
