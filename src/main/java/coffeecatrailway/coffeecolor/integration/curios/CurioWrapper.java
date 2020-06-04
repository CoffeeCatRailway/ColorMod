package coffeecatrailway.coffeecolor.integration.curios;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.client.model.ColorArtifactModel;
import coffeecatrailway.coffeecolor.common.entity.ColorMonsterEntity;
import coffeecatrailway.coffeecolor.common.item.ColorArtifactItem;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.Constants;
import top.theillusivec4.curios.api.capability.ICurio;

/**
 * @author CoffeeCatRailway
 * Created: 25/05/2020
 */
public class CurioWrapper implements ICurio {

    private final ItemStack stack;
    private ColorArtifactModel model;

    public CurioWrapper(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public void onCurioTick(String identifier, int index, LivingEntity entity) {
        ((ColorArtifactItem) stack.getItem()).onCurioTick(stack, entity);
    }

    @Override
    public boolean canEquip(String identifier, LivingEntity entity) {
        if (entity instanceof PlayerEntity)
            return CuriosIntegration.getArtifactStack((PlayerEntity) entity).stream().map(ItemStack::isEmpty).anyMatch(bool -> true) || ColorMod.SERVER_CONFIG.canWearMultiple.get();
        return false;
    }

    @Override
    public boolean canRightClickEquip() {
        return ColorMod.SERVER_CONFIG.canRightClickEquip.get();
    }

    @Override
    public void playEquipSound(LivingEntity entity) {
        entity.world.playSound(null, entity.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    @Override
    public boolean hasRender(String identifier, LivingEntity entity) {
        return ((ColorArtifactItem) this.stack.getItem()).getRenderModel().get() != null;
    }

    @Override
    public void render(String identifier, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageTicks, float headYaw, float headPitch) {
        if (this.hasRender(identifier, entity)) {
            RenderHelper.translateIfSneaking(matrixStack, entity);
            RenderHelper.rotateIfSneaking(matrixStack, entity);

            ColorArtifactItem artifact = (ColorArtifactItem) stack.getItem();

            if (this.model == null)
                this.model = artifact.getRenderModel().get();
            IVertexBuilder vertexBuilder = ItemRenderer.getBuffer(renderTypeBuffer, model.getRenderType(artifact.getModelTexture()), false, stack.hasEffect());

            DyeColor color = DyeColor.WHITE;
            CompoundNBT nbt = stack.getOrCreateTag();
            if (nbt.contains(ColorArtifactItem.TAG_COLOR, Constants.NBT.TAG_ANY_NUMERIC))
                color = DyeColor.byId(nbt.getByte(ColorArtifactItem.TAG_COLOR) & 15);
            float[] rgb = ColorMonsterEntity.createRGBColor(color);

            this.model.render(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, rgb[0], rgb[1], rgb[2], 1.0F);
            this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageTicks, headYaw, headPitch);
        }
    }
}
