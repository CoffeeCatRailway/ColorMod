package coffeecatrailway.coffeecolor.client.entity;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.client.entity.model.ColorMonsterModel;
import coffeecatrailway.coffeecolor.common.entity.ColorMonsterEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 18/05/2020
 */
@OnlyIn(Dist.CLIENT)
public class ColorMonsterRenderer extends MobRenderer<ColorMonsterEntity, ColorMonsterModel> {

    public ColorMonsterRenderer(EntityRendererManager manager) {
        super(manager, new ColorMonsterModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(ColorMonsterEntity entity) {
        return ColorMod.getLocation("textures/entity/color_monster.png");
    }

    @Override
    public void render(ColorMonsterEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Pre<ColorMonsterEntity, ColorMonsterModel>(entity, this, partialTicks, matrixStack, buffer, packedLight)))
            return;
        matrixStack.push();
        this.entityModel.swingProgress = this.getSwingProgress(entity, partialTicks);

        boolean shouldSit = entity.isPassenger() && (entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
        this.entityModel.isSitting = shouldSit;
        this.entityModel.isChild = entity.isChild();
        float f = MathHelper.interpolateAngle(partialTicks, entity.prevRenderYawOffset, entity.renderYawOffset);
        float f1 = MathHelper.interpolateAngle(partialTicks, entity.prevRotationYawHead, entity.rotationYawHead);
        float f2 = f1 - f;
        if (shouldSit && entity.getRidingEntity() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity) entity.getRidingEntity();
            f = MathHelper.interpolateAngle(partialTicks, livingentity.prevRenderYawOffset, livingentity.renderYawOffset);
            f2 = f1 - f;
            float f3 = MathHelper.wrapDegrees(f2);
            if (f3 < -85.0f)
                f3 = -85.0f;

            if (f3 >= 85.0f)
                f3 = 85.0f;

            f = f1 - f3;
            if (f3 * f3 > 2500.0f)
                f += f3 * 0.2f;

            f2 = f1 - f;
        }

        float f6 = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);
        if (entity.getPose() == Pose.SLEEPING) {
            Direction direction = entity.getBedDirection();
            if (direction != null) {
                float f4 = entity.getEyeHeight(Pose.STANDING) - 0.1f;
                matrixStack.translate(((float) (-direction.getXOffset()) * f4), 0.0d, ((float) (-direction.getZOffset()) * f4));
            }
        }

        float f7 = this.handleRotationFloat(entity, partialTicks);
        this.applyRotations(entity, matrixStack, f7, f, partialTicks);
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        this.preRenderCallback(entity, matrixStack, partialTicks);
        matrixStack.translate(0.0d, -1.501f, 0.0d);
        float f8 = 0.0f;
        float f5 = 0.0f;
        if (!shouldSit && entity.isAlive()) {
            f8 = MathHelper.lerp(partialTicks, entity.prevLimbSwingAmount, entity.limbSwingAmount);
            f5 = entity.limbSwing - entity.limbSwingAmount * (1.0f - partialTicks);
            if (entity.isChild())
                f5 *= 3.0f;

            if (f8 > 1.0f)
                f8 = 1.0f;
        }

        this.entityModel.setLivingAnimations(entity, f5, f8, partialTicks);
        this.entityModel.setRotationAngles(entity, f5, f8, f7, f2, f6);
        boolean flag = this.isVisible(entity);
        boolean flag1 = !flag && !entity.isInvisibleToPlayer(Minecraft.getInstance().player);
        RenderType rendertype = this.func_230042_a_(entity, flag, flag1);
        if (rendertype != null) {
            IVertexBuilder ivertexbuilder = buffer.getBuffer(rendertype);
            int i = getPackedOverlay(entity, this.getOverlayProgress(entity, partialTicks));
            float[] color = ColorMonsterEntity.getDyeRGB(entity.getColor());
            this.entityModel.render(matrixStack, ivertexbuilder, packedLight, i, color[0], color[1], color[2], flag1 ? 0.15f : 1.0f);
        }

        if (!entity.isSpectator())
            for (LayerRenderer<ColorMonsterEntity, ColorMonsterModel> layerrenderer : this.layerRenderers)
                layerrenderer.render(matrixStack, buffer, packedLight, entity, f5, f8, partialTicks, f7, f2, f6);

        matrixStack.pop();

        net.minecraftforge.client.event.RenderNameplateEvent renderNameplateEvent = new net.minecraftforge.client.event.RenderNameplateEvent(entity, entity.getDisplayName().getFormattedText(), this, matrixStack, buffer, packedLight);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(renderNameplateEvent);
        if (renderNameplateEvent.getResult() != net.minecraftforge.eventbus.api.Event.Result.DENY && (renderNameplateEvent.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW || this.canRenderName(entity)))
            this.renderName(entity, renderNameplateEvent.getContent(), matrixStack, buffer, packedLight);

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Post<ColorMonsterEntity, ColorMonsterModel>(entity, this, partialTicks, matrixStack, buffer, packedLight));

        Entity entityLeash = entity.getLeashHolder();
        if (entityLeash != null)
            this.renderLeash(entity, partialTicks, matrixStack, buffer, entityLeash);
    }
}
