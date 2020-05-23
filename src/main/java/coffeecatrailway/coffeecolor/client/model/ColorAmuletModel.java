package coffeecatrailway.coffeecolor.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nonnull;

public class ColorAmuletModel<T extends LivingEntity> extends EntityModel<T> {

	private final ModelRenderer amulet;
    private final ModelRenderer gem;

	public ColorAmuletModel() {
		textureWidth = 16;
		textureHeight = 16;

		amulet = new ModelRenderer(this);
		amulet.setRotationPoint(0.0F, 0.0F, -0.01F);
		amulet.setTextureOffset(0, 0).addBox(-1.5F, 5.0F, -3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		amulet.setTextureOffset(0, 6).addBox(-1.5F, 1.0F, -3.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		amulet.setTextureOffset(0, 2).addBox(-2.5F, 2.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		amulet.setTextureOffset(4, 2).addBox(1.5F, 2.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        gem = new ModelRenderer(this);
        gem.setRotationPoint(0.0F, 0.0F, -0.01F);
        gem.setTextureOffset(8, 2).addBox(-1.5F, 2.0F, -3.25F, 3.0F, 3.0F, 1.0F, 0.0F, false);
	}

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch) {
    }

    @Override
	public void render(@Nonnull MatrixStack matrixStack, @Nonnull IVertexBuilder vertexBuilder, int light, int overlay, float red, float green, float blue, float alpha){
		amulet.render(matrixStack, vertexBuilder, light, overlay);
        gem.render(matrixStack, vertexBuilder, light, overlay, red, green, blue, 1.0f);
	}
}