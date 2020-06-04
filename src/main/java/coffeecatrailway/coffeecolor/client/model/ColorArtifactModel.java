package coffeecatrailway.coffeecolor.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nonnull;

/**
 * @author CoffeeCatRailway
 * Created: 25/05/2020
 */
public abstract class ColorArtifactModel extends EntityModel<LivingEntity> {

    protected ModelRenderer artifact;
    protected ModelRenderer gem;

    public ColorArtifactModel(int textureWidth, int textureHeight) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, @Nonnull IVertexBuilder vertexBuilder, int light, int overlay, float red, float green, float blue, float alpha) {
        if (artifact != null)
            artifact.render(matrixStack, vertexBuilder, light, overlay);
        if (gem != null)
            gem.render(matrixStack, vertexBuilder, light, overlay, red, green, blue, 1.0f);
    }
}
