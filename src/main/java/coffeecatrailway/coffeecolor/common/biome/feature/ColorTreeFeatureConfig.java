package coffeecatrailway.coffeecolor.common.biome.feature;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraftforge.common.IPlantable;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 16/05/2020
 */
public class ColorTreeFeatureConfig extends BaseTreeFeatureConfig {

    public ColorTreeFeatureConfig(BlockStateProvider trunk, BlockStateProvider leaves, List<TreeDecorator> decorators, int height) {
        super(trunk, leaves, decorators, height);
    }

    @Override
    protected ColorTreeFeatureConfig setSapling(IPlantable value) {
        super.setSapling(value);
        return this;
    }

    public static <T> ColorTreeFeatureConfig deserialize(Dynamic<T> data) {
        BaseTreeFeatureConfig config = BaseTreeFeatureConfig.deserialize(data);
        return new ColorTreeFeatureConfig(config.trunkProvider, config.leavesProvider, config.decorators, config.baseHeight);
    }

    public static <T> ColorTreeFeatureConfig deserializeWhite(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.WHITE_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeOrange(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.ORANGE_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeMagenta(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.MAGENTA_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeLightBlue(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.LIGHT_BLUE_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeYellow(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.YELLOW_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeLime(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.LIME_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializePink(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.PINK_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeGray(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.GRAY_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeLightGray(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.LIGHT_GRAY_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeCyan(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.CYAN_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializePurple(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.PURPLE_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeBlue(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.BLUE_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeBrown(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.BROWN_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeGreen(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.GREEN_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeRed(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.RED_SAPLING.get());
    }

    public static <T> ColorTreeFeatureConfig deserializeBlack(Dynamic<T> data) {
        return deserialize(data).setSapling(ColorBlocks.BLACK_SAPLING.get());
    }

    public static class Builder extends BaseTreeFeatureConfig.Builder {

        private List<TreeDecorator> decorators = ImmutableList.of();
        private int height;

        public Builder(BlockStateProvider trunk, BlockStateProvider leaves) {
            super(trunk, leaves);
        }

        @Override
        public ColorTreeFeatureConfig.Builder baseHeight(int height) {
            this.height = height;
            return this;
        }

        @Override
        public ColorTreeFeatureConfig.Builder setSapling(IPlantable sapling) {
            super.setSapling(sapling);
            return this;
        }

        @Override
        public ColorTreeFeatureConfig build() {
            return new ColorTreeFeatureConfig(trunkProvider, leavesProvider, decorators, height);
        }
    }
}
