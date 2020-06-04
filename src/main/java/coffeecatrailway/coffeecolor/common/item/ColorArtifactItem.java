package coffeecatrailway.coffeecolor.common.item;

import coffeecatrailway.coffeecolor.client.model.ColorArtifactModel;
import coffeecatrailway.coffeecolor.common.IHasColor;
import coffeecatrailway.coffeecolor.integration.curios.CuriosIntegration;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public class ColorArtifactItem extends Item implements IHasColor {

    public static final String TAG_COLOR = "Color";

    private final ResourceLocation modelTexture;
    private final Supplier<ColorArtifactModel> renderModel;

    private boolean activated = false;
    private boolean hasEnchantEffect = false;

    public ColorArtifactItem(Properties properties) {
        this(properties, null, () -> null);
    }

    public ColorArtifactItem(Properties properties, ResourceLocation modelTexture, Supplier<ColorArtifactModel> renderModel) {
        super(properties.maxDamage(10));
        this.modelTexture = modelTexture;
        this.renderModel = renderModel;
    }

    public ResourceLocation getModelTexture() {
        return modelTexture;
    }

    public Supplier<ColorArtifactModel> getRenderModel() {
        return renderModel;
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
        if (this.isInGroup(group)) {
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
        AmuletEffectBuilder amuletEffect = getEffectByColor(color);

        info.add(new StringTextComponent(TextFormatting.GOLD + "Effect: " + TextFormatting.YELLOW + I18n.format(amuletEffect.getEffect().getEffectName())));

        int duration = amuletEffect.getEffect().getDuration();
        if (amuletEffect.isPersistent()) {
            info.add(new StringTextComponent(TextFormatting.GOLD + "Duration: " + TextFormatting.YELLOW + '\u221E'));
        } else {
            if (duration > 0)
                info.add(new StringTextComponent(TextFormatting.GOLD + "Duration: " + TextFormatting.YELLOW + duration + "sec"));
        }

        int cooldown = amuletEffect.getCooldown();
        if (cooldown > 0)
            info.add(new StringTextComponent(TextFormatting.GOLD + "Cooldown: " + TextFormatting.YELLOW + cooldown + "sec"));

        if (amuletEffect.getEntityEffected() != null)
            info.add(new StringTextComponent(TextFormatting.GOLD + "Effects: " + TextFormatting.YELLOW + amuletEffect.getEffectedEntityName()));
    }

    @Override
    public int getColor(ItemStack stack, int tintindex) {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains(TAG_COLOR, Constants.NBT.TAG_ANY_NUMERIC) && tintindex == 1)
            return DyeColor.byId(nbt.getByte(TAG_COLOR) & 15).getColorValue();
        return -1;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            this.hasEnchantEffect = player.getCooldownTracker().hasCooldown(this);
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || this.hasEnchantEffect;
    }

    public void onCurioTick(ItemStack stack, LivingEntity entity) {
        if (!entity.world.isRemote && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            CompoundNBT nbt = stack.getOrCreateTag();
            DyeColor color = DyeColor.byId(nbt.getByte(TAG_COLOR) & 15);
            AmuletEffectBuilder amuletEffect = getEffectByColor(color);

            if ((!player.getCooldownTracker().hasCooldown(this) && this.activated) || amuletEffect.isPersistent())
                this.applyEffect(player, amuletEffect);
            this.hasEnchantEffect = player.getCooldownTracker().hasCooldown(this);

            if (this.activated) this.activated = false;
        }
    }

    private void applyEffect(PlayerEntity player, AmuletEffectBuilder amuletEffect) {
        if (amuletEffect.getEntityEffected() != null) {
            World world = player.world;
            double radius = amuletEffect.getRadius() + (world.rand.nextDouble() * -0.5d + 0.5d);
            List<? extends LivingEntity> entities = world.getEntitiesWithinAABB(amuletEffect.getEntityEffected(), player.getBoundingBox().grow(radius));
            for (LivingEntity entity : entities)
                if (entity != player)
                    entity.addPotionEffect(new EffectInstance(amuletEffect.getEffect().getPotion(), amuletEffect.getEffect().getDuration() * 20, amuletEffect.getEffect().getAmplifier(), false, true, true));
        } else {
            player.addPotionEffect(new EffectInstance(amuletEffect.getEffect().getPotion(), amuletEffect.getEffect().getDuration() * 20, amuletEffect.getEffect().getAmplifier(), false, false, true));
        }

        if (!amuletEffect.isPersistent()) {
            player.getCooldownTracker().setCooldown(this, 20 * amuletEffect.getCooldown());
            player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.AMBIENT, 0.75f, 1.0f);
        }
    }

    public static AmuletEffectBuilder getEffectByColor(DyeColor color) {
        switch (color) {
            default:
            case WHITE:
                return new AmuletEffectBuilder(new EffectInstance(Effects.INVISIBILITY, 30)).cooldown(35);
            case ORANGE:
                return new AmuletEffectBuilder(new EffectInstance(Effects.FIRE_RESISTANCE, 60)).cooldown(30);
            case MAGENTA:
                return new AmuletEffectBuilder(new EffectInstance(Effects.STRENGTH, 25)).cooldown(30);
            case LIGHT_BLUE:
                return new AmuletEffectBuilder(new EffectInstance(Effects.SLOW_FALLING, 10)).cooldown(50);
            case YELLOW:
                return new AmuletEffectBuilder(new EffectInstance(Effects.SPEED, 10, 1)).cooldown(50);
            case LIME:
                return new AmuletEffectBuilder(new EffectInstance(Effects.JUMP_BOOST, 25, 1)).cooldown(50);
            case PINK:
                return new AmuletEffectBuilder(new EffectInstance(Effects.REGENERATION, 10, 1)).cooldown(30);
            case GRAY:
                return new AmuletEffectBuilder(new EffectInstance(Effects.INSTANT_DAMAGE, 10)).cooldown(30).effectRadius(2.75d, LivingEntity.class, "Monsters & Animals");
            case LIGHT_GRAY:
                return new AmuletEffectBuilder(new EffectInstance(Effects.POISON, 30)).cooldown(30).effectRadius(2.75d, LivingEntity.class, "Monsters & Animals");
            case CYAN:
                return new AmuletEffectBuilder(new EffectInstance(Effects.WATER_BREATHING, 60)).cooldown(60);
            case PURPLE:
                return new AmuletEffectBuilder(new EffectInstance(Effects.INSTANT_HEALTH, 5)).cooldown(60);
            case BLUE:
                return new AmuletEffectBuilder(new EffectInstance(Effects.SLOWNESS, 30, 2)).cooldown(60).effectRadius(2, LivingEntity.class, "Monsters & Animals");
            case BROWN:
                return new AmuletEffectBuilder(new EffectInstance(Effects.WEAKNESS, 30, 1)).cooldown(60).effectRadius(2, LivingEntity.class, "Monsters & Animals");
            case GREEN:
                return new AmuletEffectBuilder(new EffectInstance(Effects.LUCK, 10)).persistent();
            case RED:
                return new AmuletEffectBuilder(new EffectInstance(Effects.INSTANT_HEALTH, 30)).cooldown(75).effectRadius(2.5d, PlayerEntity.class, "Players");
            case BLACK:
                return new AmuletEffectBuilder(new EffectInstance(Effects.BLINDNESS, 45)).cooldown(90).effectRadius(2.5d, PlayerEntity.class, "Players");
        }
    }

    public void setActivated() {
        this.activated = true;
    }

    public static class AmuletEffectBuilder {

        private EffectInstance effect;
        private int cooldown = 0;
        private boolean persistent = false;

        private Class<? extends LivingEntity> entityEffected = null;
        private double radius = 0;
        private String effectedEntityName = "";

        public AmuletEffectBuilder(EffectInstance effect) {
            this.effect = effect;
        }

        public AmuletEffectBuilder persistent() {
            this.persistent = true;
            return this;
        }

        public AmuletEffectBuilder cooldown(int cooldown) {
            this.cooldown = cooldown;
            return this;
        }

        public AmuletEffectBuilder effectRadius(double radius, Class<? extends LivingEntity> entityEffected, String effectedEntityName) {
            this.radius = radius;
            this.entityEffected = entityEffected;
            this.effectedEntityName = effectedEntityName;
            return this;
        }

        public EffectInstance getEffect() {
            return effect;
        }

        public int getCooldown() {
            return cooldown;
        }

        public boolean isPersistent() {
            return persistent;
        }

        public Class<? extends LivingEntity> getEntityEffected() {
            return entityEffected;
        }

        public double getRadius() {
            return radius;
        }

        public String getEffectedEntityName() {
            return effectedEntityName;
        }
    }
}
