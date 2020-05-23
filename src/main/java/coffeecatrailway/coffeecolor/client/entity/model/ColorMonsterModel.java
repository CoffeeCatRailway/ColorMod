package coffeecatrailway.coffeecolor.client.entity.model;

import coffeecatrailway.coffeecolor.common.entity.ColorMonsterEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author CoffeeCatRailway
 * Created: 18/05/2020
 */
@OnlyIn(Dist.CLIENT)
public class ColorMonsterModel extends BipedModel<ColorMonsterEntity> {

    private final ModelRenderer tailGroup;
    private final ModelRenderer tailUpperGroup;
    private final ModelRenderer earRightGroup;
    private final ModelRenderer earLeftGroup;

    public ColorMonsterModel() {
        super(0.0f, 0.0f, 48, 48);

        this.bipedRightArm = new ModelRenderer(this);
        this.bipedLeftArm = new ModelRenderer(this);

        this.bipedRightLeg = new ModelRenderer(this);
        this.bipedRightLeg.setRotationPoint(-2.5f, 21.0f, 0.0f);
        this.bipedRightLeg.setTextureOffset(8, 18).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 3.0f, 2.0f, 0.0f, false);

        this.bipedLeftLeg = new ModelRenderer(this);
        this.bipedLeftLeg.setRotationPoint(2.5f, 21.0f, 0.0f);
        this.bipedLeftLeg.setTextureOffset(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 3.0f, 2.0f, 0.0f, false);

        this.bipedBody = new ModelRenderer(this);
        this.bipedBody.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.bipedBody.rotateAngleX = 0.1745f;
        this.bipedBody.setTextureOffset(0, 0).addBox(-4.0f, -8.0f, -1.5f, 8.0f, 8.0f, 3.0f, 0.0f, false);
        this.bipedBody.setTextureOffset(0, 11).addBox(-3.0f, -7.0f, -2.0f, 6.0f, 6.0f, 1.0f, 0.0f, false);
        this.bipedBody.setTextureOffset(14, 11).addBox(-2.0f, -6.0f, -2.25f, 4.0f, 4.0f, 1.0f, 0.0f, false);
        this.bipedBody.setTextureOffset(22, 0).addBox(-3.0f, -7.0f, 1.0f, 6.0f, 6.0f, 1.0f, 0.0f, false);

        this.tailGroup = new ModelRenderer(this);
        this.tailGroup.setRotationPoint(0.0f, -0.75f, 1.25f);
        this.bipedBody.addChild(this.tailGroup);
        this.tailGroup.rotateAngleX = 0.6109f;
        this.tailGroup.setTextureOffset(6, 23).addBox(-1.0f, -0.5614f, -0.6518f, 2.0f, 1.0f, 3.0f, 0.0f, false);

        this.tailUpperGroup = new ModelRenderer(this);
        this.tailUpperGroup.setRotationPoint(0.0f, 0.4386f, 2.4482f);
        this.tailGroup.addChild(this.tailUpperGroup);
        this.tailUpperGroup.rotateAngleX = 0.3491f;
        this.tailUpperGroup.setTextureOffset(0, 23).addBox(-0.5f, -1.0f, -0.1f, 1.0f, 1.0f, 2.0f, 0.0f, false);

        this.bipedHead = new ModelRenderer(this);
        this.bipedHead.setRotationPoint(0.0f, 14.0f, -1.0f);
        this.bipedHead.rotateAngleX = -0.1745f;
        this.bipedHead.setTextureOffset(24, 7).addBox(-2.0f, -3.0f, -2.0f, 4.0f, 3.0f, 4.0f, 0.0f, false);

        this.bipedHeadwear = new ModelRenderer(this);
        this.bipedHeadwear.setRotationPoint(0.0f, 14.0f, -1.0f);
        this.bipedHeadwear.rotateAngleX = -0.1745f;
        this.bipedHeadwear.setTextureOffset(0, 47).addBox(-1.5f, -2.0f, -2.1f, 3.0f, 1.0f, 0.0f, 0.0f, false);

        this.earRightGroup = new ModelRenderer(this);
        this.earRightGroup.setRotationPoint(-2.0f, -2.75f, 0.0f);
        this.bipedHead.addChild(this.earRightGroup);
        this.earRightGroup.rotateAngleZ = -0.1745f;
        this.earRightGroup.setTextureOffset(24, 14).addBox(-1.5f, -1.5f, -0.5f, 2.0f, 2.0f, 1.0f, 0.0f, false);

        this.earLeftGroup = new ModelRenderer(this);
        this.earLeftGroup.setRotationPoint(2.0f, -2.75f, 0.0f);
        this.bipedHead.addChild(this.earLeftGroup);
        this.earLeftGroup.rotateAngleZ = 0.0873f;
        this.earLeftGroup.setTextureOffset(30, 14).addBox(-0.5f, -1.5f, -0.5f, 2.0f, 2.0f, 1.0f, 0.0f, false);
    }

    @Override
    public void setRotationAngles(ColorMonsterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        float casualSwingCos = MathHelper.cos(ageInTicks * 0.6662f) * 0.1f;
        float casualSwingSin = MathHelper.sin(ageInTicks * 0.6662f);
        float limbSwingCos = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.25f;
        float limbSwingSin = MathHelper.sin(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.25f;

        this.bipedHead.rotateAngleX = -0.1745f + headPitch * 0.017453292f;
        this.bipedHead.rotateAngleY = headYaw * 0.017453292f;
        this.bipedHead.rotateAngleZ = casualSwingSin * 0.025f;
        this.bipedHeadwear.rotateAngleX = -0.1745f + headPitch * 0.017453292f;
        this.bipedHeadwear.rotateAngleY = headYaw * 0.017453292f;
        this.bipedHeadwear.rotateAngleZ = casualSwingCos;

        this.earRightGroup.rotateAngleZ = casualSwingSin * 0.1f;
        this.earLeftGroup.rotateAngleZ = casualSwingCos;

        this.tailGroup.rotateAngleY = casualSwingCos + limbSwingCos;
        this.tailUpperGroup.rotateAngleY = casualSwingCos + limbSwingSin;

        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4F * limbSwingAmount;
        this.bipedRightLeg.rotateAngleY = 0.0f;
        this.bipedRightLeg.rotateAngleZ = 0.0f;

        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927f) * 1.4F * limbSwingAmount;
        this.bipedLeftLeg.rotateAngleY = 0.0f;
        this.bipedLeftLeg.rotateAngleZ = 0.0f;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}