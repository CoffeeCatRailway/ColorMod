package coffeecatrailway.coffeecolor.registrate;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

/**
 * @author CoffeeCatRailway
 * Created: 23/04/2020
 */
public class BiomeBuilder<T extends Biome, P> extends AbstractBuilder<Biome, T, P, BiomeBuilder<T, P>> {

    private final NonNullFunction<Biome.Builder, T> factory;
    private final NonNullSupplier<Biome.Builder> properties = Biome.Builder::new;
    private NonNullFunction<Biome.Builder, Biome.Builder> propertiesCallback = NonNullUnaryOperator.identity();

    public BiomeBuilder(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback, NonNullFunction<Biome.Builder, T> factory) {
        super(owner, parent, name, callback, Biome.class);
        this.factory = factory;
    }

    public BiomeBuilder<T, P> properties(NonNullUnaryOperator<Biome.Builder> func) {
        this.propertiesCallback = this.propertiesCallback.andThen(func);
        return this;
    }

    public BiomeBuilder<T, P> defaultLang() {
        return this.lang(Biome::getTranslationKey);
    }

    public BiomeBuilder<T, P> lang(String name) {
        return this.lang(Biome::getTranslationKey, name);
    }

    public BiomeBuilder<T, P> defaultCategory() {
        return this.category(Biome.Category.NONE);
    }

    public BiomeBuilder<T, P> category(Biome.Category category) {
        return this.properties(prop -> prop.category(category));
    }

    public BiomeBuilder<T, P> defaultDepth() {
        return this.depth(1.0f);
    }

    public BiomeBuilder<T, P> depth(float depth) {
        return this.properties(prop -> prop.depth(depth));
    }

    public BiomeBuilder<T, P> defaultDownfall() {
        return this.downfall(1.0f);
    }

    public BiomeBuilder<T, P> downfall(float dowbfall) {
        return this.properties(prop -> prop.downfall(dowbfall));
    }

    public BiomeBuilder<T, P> defaultParent() {
        return this.parent(null);
    }

    public BiomeBuilder<T, P> parent(String parent) {
        return this.properties(prop -> prop.parent(parent));
    }

    public BiomeBuilder<T, P> defaultPrecipitation() {
        return this.precipitation(Biome.RainType.RAIN);
    }

    public BiomeBuilder<T, P> precipitation(Biome.RainType precipitation) {
        return this.properties(prop -> prop.precipitation(precipitation));
    }

    public BiomeBuilder<T, P> defaultScale() {
        return this.scale(1.0f);
    }

    public BiomeBuilder<T, P> scale(float scale) {
        return this.properties(prop -> prop.scale(scale));
    }

    public BiomeBuilder<T, P> defaultSurfaceBuilder() {
        return this.surfaceBuilder(SurfaceBuilder.DEFAULT, () -> SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG);
    }

    public <SC extends ISurfaceBuilderConfig> BiomeBuilder<T, P> surfaceBuilder(SurfaceBuilder<SC> surfaceBuilder, NonNullSupplier<SC> config) {
        return this.properties(prop -> prop.surfaceBuilder(surfaceBuilder, config.get()));
    }

    public BiomeBuilder<T, P> surfaceBuilder(ConfiguredSurfaceBuilder<?> surfaceBuilder) {
        return this.properties(prop -> prop.surfaceBuilder(surfaceBuilder));
    }

    public BiomeBuilder<T, P> defaultTemperature() {
        return this.temperature(1.0f);
    }

    public BiomeBuilder<T, P> temperature(float temperature) {
        return this.properties(prop -> prop.temperature(temperature));
    }

    public BiomeBuilder<T, P> defaultWaterColor() {
        return this.waterColor(0x3f76e4);
    }

    public BiomeBuilder<T, P> waterColor(int waterColor) {
        return this.properties(prop -> prop.waterColor(waterColor));
    }

    public BiomeBuilder<T, P> defaultWaterFogColor() {
        return this.waterFogColor(0x50533);
    }

    public BiomeBuilder<T, P> waterFogColor(int waterFogColor) {
        return this.properties(prop -> prop.waterFogColor(waterFogColor));
    }

    @Override
    protected T createEntry() {
        Biome.Builder properties = (Biome.Builder) this.properties.get();
        properties = (Biome.Builder) this.propertiesCallback.apply(properties);
        return this.factory.apply(properties);
    }
}
