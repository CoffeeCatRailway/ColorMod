package coffeecatrailway.coffeecolor.common;

import coffeecatrailway.coffeecolor.common.biome.ColorBiome;
import com.google.common.collect.Maps;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 18/05/2020
 */
public class ColorMonsterEntity extends MonsterEntity {

    private static final DataParameter<Byte> DYE_COLOR = EntityDataManager.createKey(ColorMonsterEntity.class, DataSerializers.BYTE);
    public static final Map<DyeColor, IItemProvider> DYE_BY_COLOR = Util.make(Maps.newEnumMap(DyeColor.class), (map) -> {
        map.put(DyeColor.WHITE, Items.WHITE_DYE);
        map.put(DyeColor.ORANGE, Items.ORANGE_DYE);
        map.put(DyeColor.MAGENTA, Items.MAGENTA_DYE);
        map.put(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_DYE);
        map.put(DyeColor.YELLOW, Items.YELLOW_DYE);
        map.put(DyeColor.LIME, Items.LIME_DYE);
        map.put(DyeColor.PINK, Items.PINK_DYE);
        map.put(DyeColor.GRAY, Items.GRAY_DYE);
        map.put(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_DYE);
        map.put(DyeColor.CYAN, Items.CYAN_DYE);
        map.put(DyeColor.PURPLE, Items.PURPLE_DYE);
        map.put(DyeColor.BLUE, Items.BLUE_DYE);
        map.put(DyeColor.BROWN, Items.BROWN_DYE);
        map.put(DyeColor.GREEN, Items.GREEN_DYE);
        map.put(DyeColor.RED, Items.RED_DYE);
        map.put(DyeColor.BLACK, Items.BLACK_DYE);
    });
    private static final Map<DyeColor, float[]> DYE_RGB = Maps.newEnumMap(Arrays.stream(DyeColor.values()).collect(Collectors.toMap((DyeColor dye) -> dye, ColorMonsterEntity::createSheepColor)));

    public ColorMonsterEntity(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void dropLoot(DamageSource damageSource, boolean p_213354_2_) {
        super.dropLoot(damageSource, p_213354_2_);
        this.entityDropItem(DYE_BY_COLOR.get(this.getColor()));
    }

    private static float[] createSheepColor(DyeColor dye) {
        if (dye == DyeColor.WHITE) {
            return new float[]{0.9019608F, 0.9019608F, 0.9019608F};
        } else {
            float[] rgb = dye.getColorComponentValues();
            return new float[]{rgb[0] * 0.75F, rgb[1] * 0.75F, rgb[2] * 0.75F};
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static float[] getDyeRGB(DyeColor dye) {
        return DYE_RGB.get(dye);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RestrictSunGoal(this));
        this.goalSelector.addGoal(2, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, SheepEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DYE_COLOR, (byte) 0);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_WOOD_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_WOOD_BREAK;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return true;
    }

    @Override
    public Entity getEntity() {
        return this;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putByte("Color", (byte) this.getColor().getId());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setColor(DyeColor.byId(compound.getByte("Color")));
    }

    public DyeColor getColor() {
        return DyeColor.byId(this.dataManager.get(DYE_COLOR) & 15);
    }

    public void setColor(DyeColor color) {
        byte colorId = this.dataManager.get(DYE_COLOR);
        this.dataManager.set(DYE_COLOR, (byte) (colorId & 240 | color.getId() & 15));
    }

    public static DyeColor getRandomColor(Random random) {
        return DyeColor.values()[random.nextInt(DyeColor.values().length)];
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
        DyeColor color = getRandomColor(world.getRandom());
        Biome biome = world.getBiome(this.getPosition());
        if (biome instanceof ColorBiome)
            color = ((ColorBiome) biome).getColor();
        this.setColor(color);
        return super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    }
}
