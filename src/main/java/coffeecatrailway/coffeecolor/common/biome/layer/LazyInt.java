package coffeecatrailway.coffeecolor.common.biome.layer;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class LazyInt implements IntSupplier {

    private final IntSupplier generator;
    private volatile int value;
    private volatile boolean resolved = false;

    public LazyInt(IntSupplier generator) {
        this.generator = generator;
    }

    public LazyInt(Supplier<Integer> generator) {
        this((IntSupplier) generator::get);
    }

    @Override
    public int getAsInt() {
        if (!this.resolved) {
            synchronized (this) {
                if (!this.resolved) {
                    this.value = this.generator.getAsInt();
                    this.resolved = true;
                }
            }
        }
        return this.value;
    }

    public synchronized void invalidate() {
        this.resolved = false;
    }
}
