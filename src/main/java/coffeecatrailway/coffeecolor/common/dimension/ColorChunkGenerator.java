package coffeecatrailway.coffeecolor.common.dimension;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorChunkGenerator extends NoiseChunkGenerator<ColorGenSettings> {

    private static final float[] biomeWeights = Util.make(new float[25], (weight) -> {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
                weight[i + 2 + (j + 2) * 5] = f;
            }
        }
    });

    private final OctavesNoiseGenerator depthNoise;
    private final boolean isAmplified;
    private final IWorld world;

    public ColorChunkGenerator(IWorld world, BiomeProvider provider, ColorGenSettings genSettings) {
        super(world, provider, 4, 10, 256, genSettings, true);
        this.randomSeed.skip(2620);
        this.world = world;
        this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 15, 0);
        this.isAmplified = world.getWorldInfo().getGenerator() == WorldType.AMPLIFIED;
    }

    @Override
    public void spawnMobs(WorldGenRegion region) {
        int chunkX = region.getMainChunkX();
        int chunkZ = region.getMainChunkZ();

        SharedSeedRandom random = new SharedSeedRandom();
        random.setDecorationSeed(region.getSeed(), chunkX << 4, chunkZ << 4);

        if (this.world.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING))
            super.spawnMobs(region);
    }

    @Override
    public void spawnMobs(ServerWorld world, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {
        if (this.world.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING))
            super.spawnMobs(world, spawnHostileMobs, spawnPeacefulMobs);
    }

    @Override
    protected void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {
        this.calcNoiseColumn(noiseColumn, noiseX, noiseZ, 684.412f, 684.412f, 8.555149841308594d, 4.277574920654297d, -10, 3);
    }

    @Override
    protected double func_222545_a(double xVal, double zVal, int yVal) {
        double d1 = ((double) yVal - (8.5d + xVal * 8.5d / 8.0d * 4.0d)) * 12.0d * 128.0d / 256.0d / zVal;
        if (d1 < 0.0d)
            d1 *= 4.0d;

        return d1;
    }

    @Override
    protected double[] getBiomeNoiseColumn(int chunkX, int chunkZ) {
        double[] adouble = new double[2];
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        int sea = this.getSeaLevel();
        float f3 = this.biomeProvider.getNoiseBiome(chunkX, sea, chunkZ).getDepth();

        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                Biome biome = this.biomeProvider.getNoiseBiome(chunkX + j, sea, chunkZ + k);
                float f4 = biome.getDepth();
                float f5 = biome.getScale();
                if (this.isAmplified && f4 > 0.0F) {
                    f4 = 1.0F + f4 * 2.0F;
                    f5 = 1.0F + f5 * 4.0F;
                }

                float f6 = biomeWeights[j + 2 + (k + 2) * 5] / (f4 + 2.0F);
                if (biome.getDepth() > f3)
                    f6 /= 2.0F;

                f += f5 * f6;
                f1 += f4 * f6;
                f2 += f6;
            }
        }

        f = f / f2;
        f1 = f1 / f2;
        f = f * 0.9F + 0.1F;
        f1 = (f1 * 4.0F - 1.0F) / 8.0F;
        adouble[0] = (double) f1 + this.getDepthNoise(chunkX, chunkZ);
        adouble[1] = f;
        return adouble;
    }

    private double getDepthNoise(int noiseX, int noiseZ) {
        double d0 = this.depthNoise.getValue(noiseX * 200.0d, 10.0d, noiseZ * 200.0d, 1.0d, 0.0d, true) / 8000.0d;
        if (d0 < 0.0D)
            d0 = -d0 * 0.3D;

        d0 = d0 * 3.0D - 2.0D;
        if (d0 < 0.0D)
            d0 = d0 / 28.0D;
        else {
            if (d0 > 1.0D)
                d0 = 1.0D;
            d0 = d0 / 40.0D;
        }

        return d0;
    }

    @Override
    public int getGroundHeight() {
        return this.world.getSeaLevel() + 1;
    }

    @Override
    public int getSeaLevel() {
        return ColorGenSettings.SEA_LEVEL;
    }
}
