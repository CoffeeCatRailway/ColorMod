package coffeecatrailway.coffeecolor.common.biome.feature.tree;

import coffeecatrailway.coffeecolor.common.biome.feature.ColorTreeFeatureConfig;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 16/05/2020
 */
public class ColorTreeFeature extends AbstractTreeFeature<ColorTreeFeatureConfig> {

    public ColorTreeFeature(Function<Dynamic<?>, ? extends ColorTreeFeatureConfig> function) {
        super(function);
    }

    @Override
    public boolean place(IWorldGenerationReader world, Random rand, BlockPos pos, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, ColorTreeFeatureConfig config) {
        int height = rand.nextInt(3) + rand.nextInt(2) + config.baseHeight;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (y >= 1 && y + height + 1 < world.getMaxHeight()) {
            BlockPos blockpos = pos.down();
            if (!isSoil(world, blockpos, config.getSapling())) {
                return false;
            } else if (!this.func_214615_a(world, pos, height)) {
                return false;
            } else {
                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int heightOff = height - rand.nextInt(4);
                int j1 = 2 - rand.nextInt(3);
                int xOff = x;
                int zOff = z;
                int yOff = y + height - 1;

                for (int j2 = 0; j2 < height; ++j2) {
                    if (j2 >= heightOff && j1 > 0) {
                        xOff += direction.getXOffset();
                        zOff += direction.getZOffset();
                        --j1;
                    }

                    int LogY = y + j2;
                    BlockPos blockpos1 = new BlockPos(xOff, LogY, zOff);
                    if (isAirOrLeaves(world, blockpos1)) {
                        this.func_227216_a_(world, rand, blockpos1, logPos, boundingBox, config);
                        this.func_227216_a_(world, rand, blockpos1.east(), logPos, boundingBox, config);
                        this.func_227216_a_(world, rand, blockpos1.south(), logPos, boundingBox, config);
                        this.func_227216_a_(world, rand, blockpos1.east().south(), logPos, boundingBox, config);
                    }
                }

                for (int xOff1 = -2; xOff1 <= 0; ++xOff1) {
                    for (int zOff1 = -2; zOff1 <= 0; ++zOff1) {
                        int yOff1 = -1;
                        this.func_227219_b_(world, rand, new BlockPos(xOff + xOff1, yOff + yOff1, zOff + zOff1), leavesPos, boundingBox, config);
                        this.func_227219_b_(world, rand, new BlockPos(1 + xOff - xOff1, yOff + yOff1, zOff + zOff1), leavesPos, boundingBox, config);
                        this.func_227219_b_(world, rand, new BlockPos(xOff + xOff1, yOff + yOff1, 1 + zOff - zOff1), leavesPos, boundingBox, config);
                        this.func_227219_b_(world, rand, new BlockPos(1 + xOff - xOff1, yOff + yOff1, 1 + zOff - zOff1), leavesPos, boundingBox, config);
                        if ((xOff1 > -2 || zOff1 > -1) && (xOff1 != -1 || zOff1 != -2)) {
                            yOff1 = 1;
                            this.func_227219_b_(world, rand, new BlockPos(xOff + xOff1, yOff + yOff1, zOff + zOff1), leavesPos, boundingBox, config);
                            this.func_227219_b_(world, rand, new BlockPos(1 + xOff - xOff1, yOff + yOff1, zOff + zOff1), leavesPos, boundingBox, config);
                            this.func_227219_b_(world, rand, new BlockPos(xOff + xOff1, yOff + yOff1, 1 + zOff - zOff1), leavesPos, boundingBox, config);
                            this.func_227219_b_(world, rand, new BlockPos(1 + xOff - xOff1, yOff + yOff1, 1 + zOff - zOff1), leavesPos, boundingBox, config);
                        }
                    }
                }

                if (rand.nextBoolean()) {
                    this.func_227219_b_(world, rand, new BlockPos(xOff, yOff + 2, zOff), leavesPos, boundingBox, config);
                    this.func_227219_b_(world, rand, new BlockPos(xOff + 1, yOff + 2, zOff), leavesPos, boundingBox, config);
                    this.func_227219_b_(world, rand, new BlockPos(xOff + 1, yOff + 2, zOff + 1), leavesPos, boundingBox, config);
                    this.func_227219_b_(world, rand, new BlockPos(xOff, yOff + 2, zOff + 1), leavesPos, boundingBox, config);
                }

                for (int xOff1 = -3; xOff1 <= 4; ++xOff1) {
                    for (int zOff1 = -3; zOff1 <= 4; ++zOff1) {
                        if ((xOff1 != -3 || zOff1 != -3) && (xOff1 != -3 || zOff1 != 4) && (xOff1 != 4 || zOff1 != -3) && (xOff1 != 4 || zOff1 != 4) && (Math.abs(xOff1) < 3 || Math.abs(zOff1) < 3)) {
                            this.func_227219_b_(world, rand, new BlockPos(xOff + xOff1, yOff, zOff + zOff1), leavesPos, boundingBox, config);
                        }
                    }
                }

                for (int xOff1 = -1; xOff1 <= 2; ++xOff1) {
                    for (int zOff1 = -1; zOff1 <= 2; ++zOff1) {
                        if ((xOff1 < 0 || xOff1 > 1 || zOff1 < 0 || zOff1 > 1) && rand.nextInt(3) <= 0) {
                            int i5 = rand.nextInt(3) + 2;

                            for (int l2 = 0; l2 < i5; ++l2) {
                                this.func_227216_a_(world, rand, new BlockPos(x + xOff1, yOff - l2 - 1, z + zOff1), logPos, boundingBox, config);
                            }

                            for (int j5 = -1; j5 <= 1; ++j5) {
                                for (int i3 = -1; i3 <= 1; ++i3) {
                                    this.func_227219_b_(world, rand, new BlockPos(xOff + xOff1 + j5, yOff, zOff + zOff1 + i3), leavesPos, boundingBox, config);
                                }
                            }

                            for (int k5 = -2; k5 <= 2; ++k5) {
                                for (int l5 = -2; l5 <= 2; ++l5) {
                                    if (Math.abs(k5) != 2 || Math.abs(l5) != 2) {
                                        this.func_227219_b_(world, rand, new BlockPos(xOff + xOff1 + k5, yOff - 1, zOff + zOff1 + l5), leavesPos, boundingBox, config);
                                    }
                                }
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    private boolean func_214615_a(IWorldGenerationBaseReader world, BlockPos pos, int height) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for (int l = 0; l <= height + 1; ++l) {
            int i1 = 1;
            if (l == 0)
                i1 = 0;

            if (l >= height - 1)
                i1 = 2;

            for (int j1 = -i1; j1 <= i1; ++j1)
                for (int k1 = -i1; k1 <= i1; ++k1)
                    if (!canBeReplacedByLogs(world, blockpos$mutable.setPos(i + j1, j + l, k + k1)))
                        return false;
        }

        return true;
    }
}
