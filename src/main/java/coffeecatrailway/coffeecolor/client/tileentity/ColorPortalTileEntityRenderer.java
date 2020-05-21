package coffeecatrailway.coffeecolor.client.tileentity;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.block.ColorPortalBlock;
import coffeecatrailway.coffeecolor.common.tileentity.ColorPortalTileEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
@OnlyIn(Dist.CLIENT)
public class ColorPortalTileEntityRenderer extends TileEntityRenderer<ColorPortalTileEntity> {

    public static final ResourceLocation COLOR_SKY_TEXTURE = ColorMod.getLocation("textures/environment/color_sky.png");
    public static final ResourceLocation COLOR_PORTAL_TEXTURE = ColorMod.getLocation("textures/entity/color_portal.png");

    private static final Random RANDOM = new Random(31100L);
    private static final List<RenderType> RENDER_TYPES = IntStream.range(0, 16).mapToObj((iteration) -> getColorPortal(iteration + 1)).collect(ImmutableList.toImmutableList());

    public ColorPortalTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(ColorPortalTileEntity tile, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        RANDOM.setSeed(31100L);
        double d0 = tile.getPos().distanceSq(this.renderDispatcher.renderInfo.getProjectedView(), true);
        int i = this.getPasses(d0);
        Matrix4f matrix4f = matrix.getLast().getMatrix();
        this.renderCube(tile, 0.15F, matrix4f, buffer.getBuffer(RENDER_TYPES.get(0)));

        for (int j = 1; j < i; ++j) {
            this.renderCube(tile, 2.0F / (18f - j), matrix4f, buffer.getBuffer(RENDER_TYPES.get(j)));
        }
    }

    private void renderCube(ColorPortalTileEntity tile, float p_228883_3_, Matrix4f matrix, IVertexBuilder vertexBuilder) {
        float red = (RANDOM.nextFloat() * 0.5F + 0.97F) * p_228883_3_;
        float green = (RANDOM.nextFloat() * 0.78F + 0.5F) * p_228883_3_;
        float blue = (RANDOM.nextFloat() * 0.12F + 0.11F) * p_228883_3_;
        if (tile.getBlockState().get(ColorPortalBlock.AXIS) == Direction.Axis.X) {
            this.renderFace(matrix, vertexBuilder, 0.0F, 1.0F, 0.0F, 1.0F, 0.6F, 0.6F, 0.6F, 0.6F, red, green, blue);
            this.renderFace(matrix, vertexBuilder, 0.0F, 1.0F, 1.0F, 0.0F, 0.4F, 0.4F, 0.4F, 0.4F, red, green, blue);
        } else {
            this.renderFace(matrix, vertexBuilder, 0.6F, 0.6F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, red, green, blue);
            this.renderFace(matrix, vertexBuilder, 0.4F, 0.4F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, red, green, blue);
        }
    }

    private void renderFace(Matrix4f matrix, IVertexBuilder vertexBuilder, float p_228884_4_, float p_228884_5_, float p_228884_6_, float p_228884_7_, float p_228884_8_, float p_228884_9_, float p_228884_10_, float p_228884_11_, float red, float green, float blue) {
        vertexBuilder.pos(matrix, p_228884_4_, p_228884_6_, p_228884_8_).color(red, green, blue, 1.0F).endVertex();
        vertexBuilder.pos(matrix, p_228884_5_, p_228884_6_, p_228884_9_).color(red, green, blue, 1.0F).endVertex();
        vertexBuilder.pos(matrix, p_228884_5_, p_228884_7_, p_228884_10_).color(red, green, blue, 1.0F).endVertex();
        vertexBuilder.pos(matrix, p_228884_4_, p_228884_7_, p_228884_11_).color(red, green, blue, 1.0F).endVertex();
    }

    protected int getPasses(double p_191286_1_) {
        if (p_191286_1_ > 36864.0D)
            return 1;
        else if (p_191286_1_ > 25600.0D)
            return 3;
        else if (p_191286_1_ > 16384.0D)
            return 5;
        else if (p_191286_1_ > 9216.0D)
            return 7;
        else if (p_191286_1_ > 4096.0D)
            return 9;
        else if (p_191286_1_ > 1024.0D)
            return 11;
        else if (p_191286_1_ > 576.0D)
            return 13;
        else
            return p_191286_1_ > 256.0D ? 14 : 15;
    }

    public static RenderType getColorPortal(int iteration) {
        RenderState.TransparencyState transparencyState;
        RenderState.TextureState textureState;
        if (iteration <= 1) {
            transparencyState = RenderState.TRANSLUCENT_TRANSPARENCY;
            textureState = new RenderState.TextureState(COLOR_SKY_TEXTURE, false, false);
        } else {
            transparencyState = RenderState.ADDITIVE_TRANSPARENCY;
            textureState = new RenderState.TextureState(COLOR_PORTAL_TEXTURE, false, false);
        }

        return RenderType.makeType("color_portal", DefaultVertexFormats.POSITION_COLOR, 7, 256, false, true, RenderType.State.getBuilder().transparency(transparencyState).texture(textureState).texturing(new RenderState.PortalTexturingState(iteration)).fog(RenderState.BLACK_FOG).build(false));
    }
}
