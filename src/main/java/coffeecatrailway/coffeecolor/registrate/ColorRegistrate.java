package coffeecatrailway.coffeecolor.registrate;

import coffeecatrailway.coffeecolor.ColorMod;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorRegistrate extends AbstractRegistrate<ColorRegistrate> {

    public ColorRegistrate(String modid) {
        super(modid);
    }

    public static ColorRegistrate create(String modid, IEventBus eventBus) {
        return new ColorRegistrate(modid).registerEventListeners(eventBus).itemGroup(() -> ColorMod.GROUP);
    }

    public <T extends Biome> BiomeBuilder<T, ColorRegistrate> biome(NonNullFunction<Biome.Builder, T> factory) {
        return this.biome(this, factory);
    }

    public <T extends Biome> BiomeBuilder<T, ColorRegistrate> biome(String name, NonNullFunction<Biome.Builder, T> factory) {
        return this.biome(this, name, factory);
    }

    public <T extends Biome, P> BiomeBuilder<T, P> biome(P parent, NonNullFunction<Biome.Builder, T> factory) {
        return this.biome(parent, this.currentName(), factory);
    }

    public <T extends Biome, P> BiomeBuilder<T, P> biome(P parent, String name, NonNullFunction<Biome.Builder, T> factory) {
        return this.entry(name, callback -> new BiomeBuilder<>(this, parent, name, callback, factory));
    }
}
