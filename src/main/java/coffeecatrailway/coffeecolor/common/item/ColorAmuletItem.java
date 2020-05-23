package coffeecatrailway.coffeecolor.common.item;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.IHasColor;
import coffeecatrailway.coffeecolor.curios.CuriosIntegration;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public class ColorAmuletItem extends Item implements IHasColor {

    public static final String TAG_COLOR = "Color";
    public static final ResourceLocation MODEL_TEXTURE = ColorMod.getLocation("textures/model/color_amulet.png");

    private boolean activated = false;
    private boolean hasEffect = false;

    public ColorAmuletItem(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT stackNbt = stack.getOrCreateTag();
        if (!stackNbt.contains(TAG_COLOR, Constants.NBT.TAG_ANY_NUMERIC))
            stackNbt.putByte(TAG_COLOR, (byte) (DyeColor.WHITE.getId() & 15));

        return CuriosIntegration.INSTANCE.initCap(stack);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> stacks) {
        if (group == ColorMod.GROUP) {
            for (DyeColor color : DyeColor.values()) {
                ItemStack stack = new ItemStack(this);
                stack.getOrCreateTag().putByte(TAG_COLOR, (byte) (color.getId() & 15));
                stacks.add(stack);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> info, ITooltipFlag flag) {
        CompoundNBT nbt = stack.getOrCreateTag();
        DyeColor color = DyeColor.WHITE;
        if (nbt.contains(TAG_COLOR, Constants.NBT.TAG_ANY_NUMERIC))
            color = DyeColor.byId(nbt.getByte(TAG_COLOR) & 15);
        AmuletEffect amuletEffect = this.getEffectByColor(color);

        info.add(new StringTextComponent(TextFormatting.GOLD + "Effect: " + TextFormatting.YELLOW + I18n.format(amuletEffect.effect.getEffectName())));

        int duration = amuletEffect.effect.getDuration();
        if (amuletEffect.persistent) {
            info.add(new StringTextComponent(TextFormatting.GOLD + "Duration: " + TextFormatting.YELLOW + '\u221E'));
        } else {
            if (duration > 0)
                info.add(new StringTextComponent(TextFormatting.GOLD + "Duration: " + TextFormatting.YELLOW + duration + "sec"));
        }

        int cooldown = amuletEffect.cooldown;
        if (cooldown > 0)
            info.add(new StringTextComponent(TextFormatting.GOLD + "Cooldown: " + TextFormatting.YELLOW + cooldown + "sec"));

        if (amuletEffect.entityEffected != null)
            info.add(new StringTextComponent(TextFormatting.GOLD + "Effects: " + TextFormatting.YELLOW + amuletEffect.effectedName));
    }

    @Override
    public int getColor(ItemStack stack, int tintindex) {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains(TAG_COLOR, Constants.NBT.TAG_ANY_NUMERIC) && tintindex == 1)
            return DyeColor.byId(nbt.getByte(TAG_COLOR) & 15).getColorValue();
        return -1;
    }

    public void onCurioTick(ItemStack stack, LivingEntity entity) {
        if (!entity.world.isRemote && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            CompoundNBT nbt = stack.getOrCreateTag();
            DyeColor color = DyeColor.byId(nbt.getByte(TAG_COLOR) & 15);
            AmuletEffect amuletEffect = this.getEffectByColor(color);

            if ((!player.getCooldownTracker().hasCooldown(this) && this.activated) || amuletEffect.persistent)
                this.applyEffect(player, amuletEffect);
            this.hasEffect = player.getCooldownTracker().hasCooldown(this);

            if (this.activated) this.activated = false;
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || hasEffect;
    }

    private void applyEffect(PlayerEntity player, AmuletEffect amuletEffect) {
        if (amuletEffect.entityEffected != null) {
            int radius = amuletEffect.radius;
            List<? extends LivingEntity> entities = player.world.getEntitiesWithinAABB(amuletEffect.entityEffected, player.getBoundingBox().expand(radius, radius, radius));
            for (LivingEntity entity : entities)
                if (entity != player)
                    entity.addPotionEffect(new EffectInstance(amuletEffect.effect.getPotion(), amuletEffect.effect.getDuration() * 20, amuletEffect.effect.getAmplifier(), false, true, true));
        } else {
            player.addPotionEffect(new EffectInstance(amuletEffect.effect.getPotion(), amuletEffect.effect.getDuration() * 20, amuletEffect.effect.getAmplifier(), false, false, true));
        }

        if (!amuletEffect.persistent) {
            player.getCooldownTracker().setCooldown(this, 20 * amuletEffect.cooldown);
            player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.AMBIENT, 0.75f, 1.0f);
        }
    }

    private AmuletEffect getEffectByColor(DyeColor color) {
        switch (color) {
            default:
            case WHITE:
                return new AmuletEffect(new EffectInstance(Effects.INVISIBILITY, 30), 35);
            case ORANGE:
                return new AmuletEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 60), 30);
            case MAGENTA:
                return new AmuletEffect(new EffectInstance(Effects.STRENGTH, 25), 30);
            case LIGHT_BLUE:
                return new AmuletEffect(new EffectInstance(Effects.SLOW_FALLING, 10), 50);
            case YELLOW:
                return new AmuletEffect(new EffectInstance(Effects.SPEED, 10, 1), 50);
            case LIME:
                return new AmuletEffect(new EffectInstance(Effects.JUMP_BOOST, 30, 1), 50);
            case PINK:
                return new AmuletEffect(new EffectInstance(Effects.REGENERATION, 10, 1), 30);
            case GRAY:
                return new AmuletEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 10), 30, LivingEntity.class, "Monsters", 4);
            case LIGHT_GRAY:
                return new AmuletEffect(new EffectInstance(Effects.POISON, 30), 30, LivingEntity.class, "Monsters", 4);
            case CYAN:
                return new AmuletEffect(new EffectInstance(Effects.WATER_BREATHING, 60), 60);
            case PURPLE:
                return new AmuletEffect(new EffectInstance(Effects.INSTANT_HEALTH, 5), 60);
            case BLUE:
                return new AmuletEffect(new EffectInstance(Effects.SLOWNESS, 30, 2), 60, LivingEntity.class, "Monsters", 4);
            case BROWN:
                return new AmuletEffect(new EffectInstance(Effects.WEAKNESS, 30, 1), 60, LivingEntity.class, "Monsters", 4);
            case GREEN:
                return new AmuletEffect(new EffectInstance(Effects.LUCK, 10), true);
            case RED:
                return new AmuletEffect(new EffectInstance(Effects.INSTANT_HEALTH, 30), 75, PlayerEntity.class, "Players", 3);
            case BLACK:
                return new AmuletEffect(new EffectInstance(Effects.BLINDNESS, 45), 90, PlayerEntity.class, "Players", 3);
        }
    }

    public void setActivated() {
        this.activated = true;
    }

    static class AmuletEffect {

        protected EffectInstance effect;
        protected int cooldown;
        protected boolean persistent;

        protected Class<? extends LivingEntity> entityEffected;
        protected int radius;
        public String effectedName;

        public AmuletEffect(EffectInstance effect, int cooldown) {
            this(effect, cooldown, false);
        }

        public AmuletEffect(EffectInstance effect, boolean persistent) {
            this(effect, 0, persistent);
        }

        public AmuletEffect(EffectInstance effect, int cooldown, boolean persistent) {
            this.effect = effect;
            this.cooldown = cooldown;
            this.persistent = cooldown <= 0 || persistent;
        }

        public AmuletEffect(EffectInstance effect, int cooldown, Class<? extends LivingEntity> entityEffected, String effectedName, int radius) {
            this.effect = effect;
            this.cooldown = cooldown;
            this.entityEffected = entityEffected;
            this.effectedName = effectedName;
            this.radius = radius;
        }
    }
}
