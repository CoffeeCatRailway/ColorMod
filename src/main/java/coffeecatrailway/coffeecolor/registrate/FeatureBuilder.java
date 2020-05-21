package coffeecatrailway.coffeecolor.registrate;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.gen.feature.Feature;

/**
 * @author CoffeeCatRailway
 * Created: 24/04/2020
 */
public class FeatureBuilder<T extends Feature<?>, P> extends AbstractBuilder<Feature<?>, T, P, FeatureBuilder<T, P>> {

    private final NonNullSupplier<T> factory;

    public FeatureBuilder(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback, NonNullSupplier<T> factory) {
        super(owner, parent, name, callback, Feature.class);
        this.factory = factory;
    }

    @Override
    protected T createEntry() {
        return this.factory.get();
    }
}
