package coffeecatrailway.coffeecolor.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

/**
 * @author CoffeeCatRailway
 * Created: 25/05/2020
 */
public class ColorAmuletModel extends ColorArtifactModel {

    public ColorAmuletModel() {
        super(16, 16);

        this.artifact = new ModelRenderer(this);
        this.artifact.setRotationPoint(0.0F, 0.0F, -0.01F);
        this.artifact.setTextureOffset(0, 0).addBox(-1.5F, 5.0F, -3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        this.artifact.setTextureOffset(0, 6).addBox(-1.5F, 1.0F, -3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        this.artifact.setTextureOffset(0, 2).addBox(-2.5F, 2.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        this.artifact.setTextureOffset(4, 2).addBox(1.5F, 2.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        this.gem = new ModelRenderer(this);
        this.gem.setRotationPoint(0.0F, 0.0F, -0.01F);
        this.gem.setTextureOffset(8, 2).addBox(-1.5F, 2.0F, -3.25F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch) {
    }
}