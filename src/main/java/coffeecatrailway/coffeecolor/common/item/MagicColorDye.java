package coffeecatrailway.coffeecolor.common.item;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registry.ColorTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class MagicColorDye extends Item {

    public MagicColorDye(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (ColorTags.Blocks.COLOR_PORTAL_FRAME.contains(context.getWorld().getBlockState(context.getPos()).getBlock())) {
            if (ColorBlocks.COLOR_PORTAL.get().trySpawnPortal(context.getWorld(), context.getPos().offset(context.getFace()))) {
                context.getWorld().playSound(context.getPlayer(), context.getPos().offset(context.getFace()), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
                if (!context.getPlayer().isCreative())
                    context.getItem().damageItem(1, context.getPlayer(), (entity) -> entity.sendBreakAnimation(context.getHand()));
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
