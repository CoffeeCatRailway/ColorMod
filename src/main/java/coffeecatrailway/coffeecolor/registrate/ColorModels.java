package coffeecatrailway.coffeecolor.registrate;

import coffeecatrailway.coffeecolor.ColorMod;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;

/**
 * @author CoffeeCatRailway
 * Created: 14/05/2020
 */
public class ColorModels implements NonNullConsumer<RegistrateBlockstateProvider> {

    public static final ColorModels INSTANCE = new ColorModels();

    @Override
    public void accept(RegistrateBlockstateProvider provider) {
        ResourceLocation planksTexture = ColorMod.getLocation("block/color_planks");
        ModelBuilder<BlockModelBuilder>.ElementBuilder planksModel = provider.models().withExistingParent("color_planks", new ResourceLocation("block/block"))
                .texture("planks", planksTexture).texture("particle", planksTexture).element();
        for (Direction dir : Direction.values())
            planksModel = planksModel.face(dir).texture("planks").tintindex(0).end();
        planksModel.end().assertExistence();

        ResourceLocation leavesTexture = ColorMod.getLocation("block/color_leaves");
        ModelBuilder<BlockModelBuilder>.ElementBuilder leavesModel = provider.models().withExistingParent("color_leaves", new ResourceLocation("block/block"))
                .texture("leaves", leavesTexture).texture("particle", leavesTexture).element();
        for (Direction dir : Direction.values())
            leavesModel = leavesModel.face(dir).texture("leaves").tintindex(0).end();
        leavesModel.end().assertExistence();

        ResourceLocation logSideTexture = ColorMod.getLocation("block/color_log_side");
        ResourceLocation logEndTexture = ColorMod.getLocation("block/color_log_top");
        ResourceLocation logSideOverlayTexture = ColorMod.getLocation("block/color_log_side_overlay");
        ResourceLocation logEndOverlayTexture = ColorMod.getLocation("block/color_log_top_overlay");
        ResourceLocation strippedLogSideTexture = ColorMod.getLocation("block/stripped_color_log_side");
        ResourceLocation strippedLogEndTexture = ColorMod.getLocation("block/stripped_color_log_top");
        ResourceLocation strippedLogSideOverlayTexture = ColorMod.getLocation("block/stripped_color_log_side_overlay");
        ResourceLocation strippedLogEndOverlayTexture = ColorMod.getLocation("block/stripped_color_log_top_overlay");

        ModelBuilder<BlockModelBuilder> colorLogDefault = provider.models().withExistingParent("color_log_default", ColorMod.getLocation("block/color_log"))
                .texture("side", logSideTexture)
                .texture("end", logEndTexture)
                .texture("side_overlay", logSideOverlayTexture)
                .texture("end_overlay", logEndOverlayTexture);
        colorLogDefault.assertExistence();
        ModelBuilder<BlockModelBuilder> strippedColorLogDefault = provider.models().withExistingParent("stripped_color_log", ColorMod.getLocation("block/color_log"))
                .texture("side", strippedLogSideTexture)
                .texture("end", strippedLogEndTexture)
                .texture("side_overlay", strippedLogSideOverlayTexture)
                .texture("end_overlay", strippedLogEndOverlayTexture);
        strippedColorLogDefault.assertExistence();
        ModelBuilder<BlockModelBuilder> colorWoodDefault = provider.models().withExistingParent("color_wood", ColorMod.getLocation("block/color_log"))
                .texture("side", logSideTexture)
                .texture("end", logSideTexture)
                .texture("side_overlay", logSideOverlayTexture)
                .texture("end_overlay", logSideOverlayTexture);
        colorWoodDefault.assertExistence();
        ModelBuilder<BlockModelBuilder> strippedColorWoodDefault = provider.models().withExistingParent("stripped_color_wood", ColorMod.getLocation("block/color_log"))
                .texture("side", strippedLogSideTexture)
                .texture("end", strippedLogSideTexture)
                .texture("side_overlay", strippedLogSideOverlayTexture)
                .texture("end_overlay", strippedLogSideOverlayTexture);
        strippedColorWoodDefault.assertExistence();

        ResourceLocation color_cross = ColorMod.getLocation("block/color_cross");
        ModelBuilder<BlockModelBuilder> sapling = provider.models().withExistingParent("color_sapling", color_cross)
                .texture("cross", ColorMod.getLocation("block/color_sapling"));
        sapling.assertExistence();
        ModelBuilder<BlockModelBuilder> grass = provider.models().withExistingParent("color_grass", color_cross)
                .texture("cross", ColorMod.getLocation("block/color_grass"));
        grass.assertExistence();
        ModelBuilder<BlockModelBuilder> tall_grass_bottom = provider.models().withExistingParent("color_tall_grass_bottom", color_cross)
                .texture("cross", ColorMod.getLocation("block/color_tall_grass_bottom"));
        tall_grass_bottom.assertExistence();
        ModelBuilder<BlockModelBuilder> tall_grass_top = provider.models().withExistingParent("color_tall_grass_top", color_cross)
                .texture("cross", ColorMod.getLocation("block/color_tall_grass_top"));
        tall_grass_top.assertExistence();
    }
}
