package coffeecatrailway.coffeecolor.common.biome.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.SimplePlacement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author CoffeeCatRailway
 * Created: 23/05/2020
 */
public class Height4To255 extends SimplePlacement<NoPlacementConfig> {

    public Height4To255(Function<Dynamic<?>, ? extends NoPlacementConfig> config) {
        super(config);
    }

    @Override
    protected Stream<BlockPos> getPositions(Random random, NoPlacementConfig config, BlockPos pos) {
        int count = 20 + random.nextInt(50);
        return IntStream.range(1, count).mapToObj(value -> {
            int x = random.nextInt(16) + pos.getX();
            int z = random.nextInt(16) + pos.getZ();
            int y = random.nextInt(250) + 4;
            return new BlockPos(x, y, z);
        });
    }
}
