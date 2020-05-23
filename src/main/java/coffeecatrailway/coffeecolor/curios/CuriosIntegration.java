package coffeecatrailway.coffeecolor.curios;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.client.model.ColorAmuletModel;
import coffeecatrailway.coffeecolor.common.entity.ColorMonsterEntity;
import coffeecatrailway.coffeecolor.common.item.ColorAmuletItem;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;
import top.theillusivec4.curios.api.capability.ICurioItemHandler;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public class CuriosIntegration {

    public static CuriosIntegration INSTANCE;

    public static void init() {
        INSTANCE = new CuriosIntegration();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CuriosIntegration::sendImc);
    }

    public static void sendImc(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("necklace"));

        ColorMod.LOGGER.info("Common Event: Register curios types");
    }

    public static ItemStack getAmuletStack(PlayerEntity player) {
        AtomicReference<ItemStack> amulet = new AtomicReference<>(ItemStack.EMPTY);
        LazyOptional<ICurioItemHandler> optional = CuriosAPI.getCuriosHandler(player);
        optional.ifPresent(handler -> {
            if (handler.getStackInSlot("necklace", 0).getItem() instanceof ColorAmuletItem)
                amulet.set(handler.getStackInSlot("necklace", 0));
        });
        return amulet.get();
    }

    public ICapabilityProvider initCap(ItemStack stack) {
        return new ICapabilityProvider() {

            private final LazyOptional<ICurio> opt = LazyOptional.of(() -> new Wrapper(stack));

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                return CuriosCapability.ITEM.orEmpty(cap, opt);
            }
        };
    }

    public static class Wrapper implements ICurio {

        private final ItemStack stack;
        private ColorAmuletModel<?> amuletModel;

        Wrapper(ItemStack stack) {
            this.stack = stack;
        }

        @Override
        public void onCurioTick(String identifier, int index, LivingEntity entity) {
            ((ColorAmuletItem) stack.getItem()).onCurioTick(stack, entity);
        }

        @Override
        public boolean canEquip(String identifier, LivingEntity entity) {
            return !CuriosAPI.getCurioEquipped(stack.getItem(), entity).isPresent();
        }

        @Override
        public boolean canRightClickEquip() {
            return true;
        }

        @Override
        public void playEquipSound(LivingEntity entity) {
            entity.world.playSound(null, entity.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }

        @Override
        public boolean hasRender(String identifier, LivingEntity livingEntity) {
            return true;
        }

        @Override
        public void render(String identifier, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            RenderHelper.translateIfSneaking(matrixStack, livingEntity);
            RenderHelper.rotateIfSneaking(matrixStack, livingEntity);
            if (this.amuletModel == null)
                this.amuletModel = new ColorAmuletModel();

            ColorAmuletModel<?> amuletModel = this.amuletModel;
            IVertexBuilder vertexBuilder = ItemRenderer.getBuffer(renderTypeBuffer, amuletModel.getRenderType(ColorAmuletItem.MODEL_TEXTURE), false, stack.hasEffect());
            DyeColor color = DyeColor.WHITE;
            CompoundNBT nbt = stack.getOrCreateTag();
            if (nbt.contains(ColorAmuletItem.TAG_COLOR, Constants.NBT.TAG_ANY_NUMERIC))
                color = DyeColor.byId(nbt.getByte(ColorAmuletItem.TAG_COLOR) & 15);
            float[] rgb = ColorMonsterEntity.createRGBColor(color);
            amuletModel.render(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, rgb[0], rgb[1], rgb[2], 1.0F);
        }
    }
}
