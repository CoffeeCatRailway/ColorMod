package coffeecatrailway.coffeecolor.common.dimension;

import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registrate.ColorTags;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorTeleporter extends Teleporter implements ITeleporter {

    private final Object2LongMap<BlockPos> field_222275_f = new Object2LongOpenHashMap<>();
    protected final Map<BlockPos, PortalPosition> destinationCoordinateCache = Maps.newHashMapWithExpectedSize(4096);

    public ColorTeleporter(ServerWorld world) {
        super(world);
    }

    @Override
    public boolean placeInPortal(Entity entity, float rotationYaw) {
        BlockPos blockPos = entity.getPosition();

        double distance = -1.0D;
        boolean doesPortalExist = true;
        BlockPos location = BlockPos.ZERO;

        if (this.destinationCoordinateCache.containsKey(blockPos)) {
            final PortalPosition portalPosition = this.destinationCoordinateCache.get(blockPos);
            distance = 0.0D;
            location = portalPosition.pos;
            portalPosition.lastUpdateTime = this.world.getGameTime();
            doesPortalExist = false;
        } else {
            final BlockPos entityPos = new BlockPos(entity);
            for (int offsetX = -128; offsetX <= 128; ++offsetX) {
                BlockPos positionCache;
                for (int offsetZ = -128; offsetZ <= 128; ++offsetZ) {
                    for (BlockPos currentPos = entityPos.add(offsetX, this.world.getActualHeight() - 1 - entityPos.getY(), offsetZ); currentPos.getY() >= 0; currentPos = positionCache) {
                        positionCache = currentPos.down();
                        if (this.world.getBlockState(currentPos).getBlock() == ColorBlocks.COLOR_PORTAL.get()) {
                            while (this.world.getBlockState(positionCache = currentPos.down()).getBlock() == ColorBlocks.COLOR_PORTAL.get())
                                currentPos = positionCache;

                            final double distanceToEntity = currentPos.distanceSq(entityPos);
                            if (distance < 0.0D || distanceToEntity < distance) {
                                distance = distanceToEntity;
                                location = currentPos;
                            }
                        }
                    }
                }
            }
        }

        if (distance >= 0.0D) {
            if (doesPortalExist)
                this.destinationCoordinateCache.put(blockPos, new PortalPosition(location, this.world.getWorld().getGameTime()));

            double tpX = location.getX() + 0.5D;
            double tpY = location.getY() + 0.5D;
            double tpZ = location.getZ() + 0.5D;
            Direction direction = null;

            if (this.world.getBlockState(location.west()).getBlock() == ColorBlocks.COLOR_PORTAL.get())
                direction = Direction.NORTH;

            if (this.world.getBlockState(location.east()).getBlock() == ColorBlocks.COLOR_PORTAL.get())
                direction = Direction.SOUTH;

            if (this.world.getBlockState(location.north()).getBlock() == ColorBlocks.COLOR_PORTAL.get())
                direction = Direction.EAST;

            if (this.world.getBlockState(location.south()).getBlock() == ColorBlocks.COLOR_PORTAL.get())
                direction = Direction.WEST;

            final Direction enumfacing1 = Direction.byHorizontalIndex(MathHelper.floor(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3);

            if (direction != null) {
                Direction enumfacing2 = direction.rotateYCCW();
                final BlockPos blockpos2 = location.offset(direction);
                boolean flag2 = this.isInsideBlock(blockpos2);
                boolean flag3 = this.isInsideBlock(blockpos2.offset(enumfacing2));

                if (flag3 && flag2) {
                    location = location.offset(enumfacing2);
                    direction = direction.getOpposite();
                    enumfacing2 = enumfacing2.getOpposite();
                    final BlockPos blockpos3 = location.offset(direction);
                    flag2 = this.isInsideBlock(blockpos3);
                    flag3 = this.isInsideBlock(blockpos3.offset(enumfacing2));
                }

                float f6 = 0.5F;
                float f1 = 0.5F;

                if (!flag3 && flag2)
                    f6 = 1.0F;
                else if (flag3 && !flag2)
                    f6 = 0.0F;
                else if (flag3)
                    f1 = 0.0F;

                tpX = location.getX() + 0.5D;
                tpY = location.getY() + 0.5D;
                tpZ = location.getZ() + 0.5D;
                tpX += enumfacing2.getXOffset() * f6 + direction.getXOffset() * f1;
                tpZ += enumfacing2.getYOffset() * f6 + direction.getYOffset() * f1;
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;

                if (direction == enumfacing1) {
                    f2 = 1.0F;
                    f3 = 1.0F;
                } else if (direction == enumfacing1.getOpposite()) {
                    f2 = -1.0F;
                    f3 = -1.0F;
                } else if (direction == enumfacing1.rotateY()) {
                    f4 = 1.0F;
                    f5 = -1.0F;
                } else {
                    f4 = -1.0F;
                    f5 = 1.0F;
                }

                final double d2 = entity.getMotion().getX();
                final double d3 = entity.getMotion().getZ();
                entity.setMotion(d2 * f2 + d3 * f5, 0.0F, d2 * f4 + d3 * f3);
                entity.rotationYaw = rotationYaw - enumfacing1.getHorizontalIndex() * 90 + direction.getHorizontalIndex() * 90;
            } else
                entity.setMotion(0.0F, 0.0F, 0.0F);

            if (entity instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) entity).connection.setPlayerLocation(tpX, tpY, tpZ, entity.rotationYaw, entity.rotationPitch);
                ((ServerPlayerEntity) entity).connection.captureCurrentPosition();
            } else
                entity.setLocationAndAngles(tpX, tpY, tpZ, entity.rotationYaw, entity.rotationPitch);
            return true;
        } else
            return false;
    }

    private boolean isInsideBlock(BlockPos position) {
        return !this.world.isAirBlock(position) || !this.world.isAirBlock(position.up());
    }

    @Override
    public boolean makePortal(Entity entity) {
        return createPortal(this.world, new BlockPos(MathHelper.floor(entity.getPosX()), MathHelper.floor(entity.getPosY()), MathHelper.floor(entity.getPosZ())), entity);
    }

    public static boolean createPortal(World world, BlockPos pos, @Nullable Entity entity) {
        int i = 16;
        double d0 = -1.0D;
        int j = MathHelper.floor(entity.getPosX());
        int k = MathHelper.floor(entity.getPosY());
        int l = MathHelper.floor(entity.getPosZ());
        int i1 = j;
        int j1 = k;
        int k1 = l;
        int l1 = 0;
        int i2 = world.rand.nextInt(4);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for (int j2 = j - 16; j2 <= j + 16; ++j2) {
            double d1 = (double) j2 + 0.5D - entity.getPosX();

            for (int l2 = l - 16; l2 <= l + 16; ++l2) {
                double d2 = (double) l2 + 0.5D - entity.getPosZ();

                label276:
                for (int j3 = world.getActualHeight() - 1; j3 >= 0; --j3) {
                    if (world.isAirBlock(blockpos$mutable.setPos(j2, j3, l2))) {
                        while (j3 > 0 && world.isAirBlock(blockpos$mutable.setPos(j2, j3 - 1, l2))) {
                            --j3;
                        }

                        for (int k3 = i2; k3 < i2 + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;
                            if (k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4) {
                                for (int k4 = 0; k4 < 4; ++k4) {
                                    for (int l4 = -1; l4 < 4; ++l4) {
                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
                                        int j5 = j3 + l4;
                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
                                        blockpos$mutable.setPos(i5, j5, k5);
                                        if (l4 < 0 && !world.getBlockState(blockpos$mutable).getMaterial().isSolid() || l4 >= 0 && !world.isAirBlock(blockpos$mutable)) {
                                            continue label276;
                                        }
                                    }
                                }
                            }

                            double d5 = (double) j3 + 0.5D - entity.getPosY();
                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;
                            if (d0 < 0.0D || d7 < d0) {
                                d0 = d7;
                                i1 = j2;
                                j1 = j3;
                                k1 = l2;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (int l5 = j - 16; l5 <= j + 16; ++l5) {
                double d3 = (double) l5 + 0.5D - entity.getPosX();

                for (int j6 = l - 16; j6 <= l + 16; ++j6) {
                    double d4 = (double) j6 + 0.5D - entity.getPosZ();

                    label214:
                    for (int i7 = world.getActualHeight() - 1; i7 >= 0; --i7) {
                        if (world.isAirBlock(blockpos$mutable.setPos(l5, i7, j6))) {
                            while (i7 > 0 && world.isAirBlock(blockpos$mutable.setPos(l5, i7 - 1, j6))) {
                                --i7;
                            }

                            for (int l7 = i2; l7 < i2 + 2; ++l7) {
                                int l8 = l7 % 2;
                                int k9 = 1 - l8;

                                for (int i10 = 0; i10 < 4; ++i10) {
                                    for (int k10 = -1; k10 < 4; ++k10) {
                                        int i11 = l5 + (i10 - 1) * l8;
                                        int j11 = i7 + k10;
                                        int k11 = j6 + (i10 - 1) * k9;
                                        blockpos$mutable.setPos(i11, j11, k11);
                                        if (k10 < 0 && !world.getBlockState(blockpos$mutable).getMaterial().isSolid() || k10 >= 0 && !world.isAirBlock(blockpos$mutable)) {
                                            continue label214;
                                        }
                                    }
                                }

                                double d6 = (double) i7 + 0.5D - entity.getPosY();
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;
                                if (d0 < 0.0D || d8 < d0) {
                                    d0 = d8;
                                    i1 = l5;
                                    j1 = i7;
                                    k1 = j6;
                                    l1 = l7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i6 = i1;
        int k2 = j1;
        int k6 = k1;
        int l6 = l1 % 2;
        int i3 = 1 - l6;
        if (l1 % 4 >= 2) {
            l6 = -l6;
            i3 = -i3;
        }

        if (d0 < 0.0D) {
            j1 = MathHelper.clamp(j1, 70, world.getActualHeight() - 10);
            k2 = j1;

            for (int j7 = -1; j7 <= 1; ++j7) {
                for (int i8 = 1; i8 < 3; ++i8) {
                    for (int i9 = -1; i9 < 3; ++i9) {
                        int l9 = i6 + (i8 - 1) * l6 + j7 * i3;
                        int j10 = k2 + i9;
                        int l10 = k6 + (i8 - 1) * i3 - j7 * l6;
                        boolean flag = i9 < 0;
                        blockpos$mutable.setPos(l9, j10, l10);
                        world.setBlockState(blockpos$mutable, flag ? ColorTags.Blocks.COLOR_PORTAL_FRAME.getRandomElement(world.rand).getDefaultState() : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        for (int k7 = -1; k7 < 3; ++k7) {
            for (int j8 = -1; j8 < 4; ++j8) {
                if (k7 == -1 || k7 == 2 || j8 == -1 || j8 == 3) {
                    blockpos$mutable.setPos(i6 + k7 * l6, k2 + j8, k6 + k7 * i3);
                    world.setBlockState(blockpos$mutable, ColorTags.Blocks.COLOR_PORTAL_FRAME.getRandomElement(world.rand).getDefaultState(), 3);
                }
            }
        }

        BlockState blockstate = ColorBlocks.COLOR_PORTAL.get().getDefaultState().with(NetherPortalBlock.AXIS, l6 == 0 ? Direction.Axis.Z : Direction.Axis.X);

        for (int k8 = 0; k8 < 2; ++k8) {
            for (int j9 = 0; j9 < 3; ++j9) {
                blockpos$mutable.setPos(i6 + k8 * l6, k2 + j9, k6 + k8 * i3);
                world.setBlockState(blockpos$mutable, blockstate, 18);
            }
        }

        return true;
    }

    static class PortalPosition {

        public final BlockPos pos;
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long lastUpdateTime) {
            this.pos = pos;
            this.lastUpdateTime = lastUpdateTime;
        }
    }
}
