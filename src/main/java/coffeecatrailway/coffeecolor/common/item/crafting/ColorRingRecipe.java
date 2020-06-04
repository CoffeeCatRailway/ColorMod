package coffeecatrailway.coffeecolor.common.item.crafting;

import coffeecatrailway.coffeecolor.common.item.ColorArtifactItem;
import coffeecatrailway.coffeecolor.common.item.ColorGemItem;
import coffeecatrailway.coffeecolor.registrate.ColorTags;
import coffeecatrailway.coffeecolor.registry.ColorItems;
import coffeecatrailway.coffeecolor.registry.ColorRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * @author CoffeeCatRailway
 * Created: 23/05/2020
 */
public class ColorRingRecipe extends SpecialRecipe {

    public ColorRingRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        ItemStack gemStack = this.hasColorGem(inv);
        if (!gemStack.isEmpty()) {
            ColorGemItem gem = (ColorGemItem) gemStack.getItem();
            Item goldIngot = Items.GOLD_INGOT;
            return inv.getStackInSlot(1).getItem() == gem
                    && inv.getStackInSlot(3).getItem() == goldIngot
                    && inv.getStackInSlot(4).getItem() == Items.DIAMOND
                    && inv.getStackInSlot(5).getItem() == goldIngot
                    && inv.getStackInSlot(7).getItem() == goldIngot;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
        ItemStack gemStack = this.hasColorGem(inv);
        if (!gemStack.isEmpty()) {
            ColorGemItem gem = (ColorGemItem) gemStack.getItem();
            ItemStack amulet = new ItemStack(ColorItems.COLOR_RING.get());
            amulet.getOrCreateTag().putByte(ColorArtifactItem.TAG_COLOR, (byte) (gem.getColorByValue(gem.getColor()).getId() & 15));
            return amulet;
        }
        return gemStack;
    }

    private ItemStack hasColorGem(CraftingInventory inv) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (ColorTags.Items.COLOR_GEMS.contains(stack.getItem()))
                return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ColorRecipes.COLOR_RING_SERIALIZER.get();
    }
}
