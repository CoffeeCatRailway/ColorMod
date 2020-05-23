package coffeecatrailway.coffeecolor.common.block;

import coffeecatrailway.coffeecolor.common.dimension.ColorGenSettings;
import coffeecatrailway.coffeecolor.common.dimension.ColorTeleporter;
import coffeecatrailway.coffeecolor.common.tileentity.ColorPortalTileEntity;
import coffeecatrailway.coffeecolor.registry.ColorBiomes;
import coffeecatrailway.coffeecolor.registry.ColorBlocks;
import coffeecatrailway.coffeecolor.registrate.ColorTags;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorPortalBlock extends ContainerBlock {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public ColorPortalBlock(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.get(AXIS)) {
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new ColorPortalTileEntity();
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    public boolean trySpawnPortal(World world, BlockPos pos) {
        ColorPortalBlock.Size size = this.isPortal(world, pos);
        if (size != null && this.canCreatePortalByWorld(world)) {
            size.placePortalBlocks();
            return true;
        } else {
            return false;
        }
    }

    private boolean canCreatePortalByWorld(World world) {
        return (world.dimension.getType() == DimensionType.OVERWORLD || world.dimension.getType() == ColorBiomes.COLOR_DIMENSION_TYPE);
    }

    @Nullable
    public ColorPortalBlock.Size isPortal(IWorld world, BlockPos pos) {
        ColorPortalBlock.Size sixeX = new ColorPortalBlock.Size(world, pos, Direction.Axis.X);
        if (sixeX.isValid()) {
            return sixeX;
        } else {
            ColorPortalBlock.Size sizeZ = new ColorPortalBlock.Size(world, pos, Direction.Axis.Z);
            return sizeZ.isValid() ? sizeZ : null;
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = state.get(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && facingState.getBlock() != this && !(new ColorPortalBlock.Size(world, pos, direction$axis1)).canCreatePortal() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, pos, facingPos);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (!worldIn.isRemote && !entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.isNonBoss()) {
            MinecraftServer server = worldIn.getServer();

            if (entityIn.dimension.getModType() == ColorBiomes.COLOR_DIMENSION.get()) {
                if (server != null && entityIn.timeUntilPortal <= 0) {
                    DimensionType warptype = DimensionType.OVERWORLD;
                    entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    if (entityIn.timeUntilPortal > 0) {
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    }

                    if (entityIn instanceof ServerPlayerEntity) {
                        this.changePlayerDimension(warptype, (ServerPlayerEntity) entityIn);
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    } else {
                        this.changeDimension(warptype, entityIn);
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    }
                } else {
                    entityIn.timeUntilPortal = Math.max(entityIn.getPortalCooldown(), 100);
                }
            } else {
                if (server != null && entityIn.timeUntilPortal <= 0) {
                    DimensionType warptype = ColorBiomes.COLOR_DIMENSION_TYPE;
                    entityIn.timeUntilPortal = entityIn.getPortalCooldown();

                    if (entityIn.timeUntilPortal > 0) {
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    }

                    if (entityIn instanceof ServerPlayerEntity) {
                        this.changePlayerDimension(warptype, (ServerPlayerEntity) entityIn);
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    } else {
                        this.changeDimension(warptype, entityIn);
                        entityIn.timeUntilPortal = entityIn.getPortalCooldown();
                    }
                } else {
                    entityIn.timeUntilPortal = Math.max(entityIn.getPortalCooldown(), 100);
                }
            }
        }
    }

    public Entity changePlayerDimension(DimensionType destination, ServerPlayerEntity serverPlayerEntity) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(serverPlayerEntity, destination)) return null;
        serverPlayerEntity.setInvulnerable(true);
        DimensionType dimensiontype = serverPlayerEntity.dimension;

        ServerWorld serverworld = serverPlayerEntity.server.getWorld(dimensiontype);
        serverPlayerEntity.dimension = destination;
        ServerWorld serverworld1 = serverPlayerEntity.server.getWorld(destination);

        ColorTeleporter teleporter = new ColorTeleporter(serverworld1);

        WorldInfo worldinfo = serverPlayerEntity.world.getWorldInfo();
        serverPlayerEntity.connection.sendPacket(new SRespawnPacket(destination, WorldInfo.byHashing(worldinfo.getSeed()), worldinfo.getGenerator(), serverPlayerEntity.interactionManager.getGameType()));
        serverPlayerEntity.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        PlayerList playerlist = serverPlayerEntity.server.getPlayerList();
        playerlist.updatePermissionLevel(serverPlayerEntity);
        serverworld.removeEntity(serverPlayerEntity, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
        serverPlayerEntity.revive();
        double d0 = serverPlayerEntity.getPosX();
        double d1 = serverPlayerEntity.getPosY();
        double d2 = serverPlayerEntity.getPosZ();
        float f = serverPlayerEntity.rotationPitch;
        float f1 = serverPlayerEntity.rotationYaw;
        float f2 = f1;
        serverworld.getProfiler().startSection("moving");
        double moveFactor = serverworld.getDimension().getMovementFactor() / serverworld1.getDimension().getMovementFactor();
        d0 *= moveFactor;
        d2 *= moveFactor;

        serverPlayerEntity.setLocationAndAngles(d0, d1, d2, f1, f);
        serverworld.getProfiler().endSection();
        serverworld.getProfiler().startSection("placing");
        double d7 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minX() + 16.0D);
        double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minZ() + 16.0D);
        double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxX() - 16.0D);
        double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxZ() - 16.0D);
        d0 = MathHelper.clamp(d0, d7, d5);
        d2 = MathHelper.clamp(d2, d4, d6);
        serverPlayerEntity.setLocationAndAngles(d0, d1, d2, f1, f);
        if (!teleporter.placeInPortal(serverPlayerEntity, f2)) {
            teleporter.makePortal(serverPlayerEntity);
            teleporter.placeInPortal(serverPlayerEntity, f2);
        }

        serverworld.getProfiler().endSection();
        serverPlayerEntity.setWorld(serverworld1);
        serverworld1.addNewPlayer(serverPlayerEntity);
        serverPlayerEntity.connection.setPlayerLocation(serverPlayerEntity.getPosX(), serverPlayerEntity.getPosY(), serverPlayerEntity.getPosZ(), f1, f);
        serverPlayerEntity.interactionManager.setWorld(serverworld1);
        serverPlayerEntity.connection.sendPacket(new SPlayerAbilitiesPacket(serverPlayerEntity.abilities));
        playerlist.sendWorldInfo(serverPlayerEntity, serverworld1);
        playerlist.sendInventory(serverPlayerEntity);

        for (EffectInstance effectinstance : serverPlayerEntity.getActivePotionEffects())
            serverPlayerEntity.connection.sendPacket(new SPlayEntityEffectPacket(serverPlayerEntity.getEntityId(), effectinstance));

        serverPlayerEntity.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
        serverPlayerEntity.lastExperience = -1;
        serverPlayerEntity.lastHealth = -1.0F;
        serverPlayerEntity.lastFoodLevel = -1;
        net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(serverPlayerEntity, dimensiontype, destination);
        return serverPlayerEntity;
    }

    @Nullable
    public Entity changeDimension(DimensionType destination, Entity entity) {
        if (!entity.world.isRemote && !entity.removed) {
            entity.world.getProfiler().startSection("changeDimension");
            MinecraftServer minecraftserver = entity.getServer();
            DimensionType dimensiontype = entity.dimension;
            ServerWorld serverworld = minecraftserver.getWorld(dimensiontype);
            ServerWorld serverworld1 = minecraftserver.getWorld(destination);

            ColorTeleporter teleporter = new ColorTeleporter(serverworld1);
            entity.dimension = destination;
            entity.detach();
            entity.world.getProfiler().startSection("reposition");
            Vec3d vec3d = entity.getMotion();

            entity.world.getProfiler().endStartSection("reloading");
            Entity entity2 = entity.getType().create(serverworld1);
            if (entity2 != null) {
                entity2.copyDataFromOld(entity);
                teleporter.placeInPortal(entity2, entity2.rotationYaw);
                entity2.setMotion(vec3d);
                serverworld1.addEntityIfNotDuplicate(entity2);
                entity.remove();
            }

            entity2.world.getProfiler().endSection();
            serverworld.resetUpdateEntityTick();
            serverworld1.resetUpdateEntityTick();
            entity2.world.getProfiler().endSection();
            return entity2;
        } else
            return null;
    }

    public static class Size {

        private final IWorld world;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private final Direction leftDir;
        private int portalBlockCount;
        @Nullable
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public Size(IWorld world, BlockPos pos, Direction.Axis axis) {
            this.world = world;
            this.axis = axis;
            if (axis == Direction.Axis.X) {
                this.leftDir = Direction.EAST;
                this.rightDir = Direction.WEST;
            } else {
                this.leftDir = Direction.NORTH;
                this.rightDir = Direction.SOUTH;
            }

            BlockPos blockpos = pos;
            while (pos.getY() > ColorGenSettings.SEA_LEVEL && pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && isEmptyBlock(world.getBlockState(pos.down())))
                pos = pos.down();

            int i = getDistanceUntilEdge(pos, leftDir) - 1;

            if (i >= 0) {
                bottomLeft = pos.offset(leftDir, i);
                width = this.getDistanceUntilEdge(bottomLeft, rightDir);

                if (width < 2 || width > 21) {
                    bottomLeft = null;
                    width = 0;
                }
            }

            if (this.bottomLeft != null)
                height = calculatePortalHeight();
        }

        protected int getDistanceUntilEdge(BlockPos pos, Direction facing) {
            int i;

            for (i = 0; i < 22; ++i) {
                BlockPos blockpos = pos.offset(facing, i);

                if (!isEmptyBlock(world.getBlockState(blockpos)) || !isFrameBlock(world.getBlockState(blockpos.down()))) {
                    break;
                }
            }

            Block block = world.getBlockState(pos.offset(facing, i)).getBlock();
            return isFrameBlock(block.getDefaultState()) ? i : 0;
        }

        protected int calculatePortalHeight() {
            label56:
            for (this.height = 0; this.height < 21; ++this.height) {
                for (int i = 0; i < this.width; ++i) {
                    BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
                    BlockState blockstate = this.world.getBlockState(blockpos);
                    if (!this.isEmptyBlock(blockstate)) {
                        break label56;
                    }

                    Block block = blockstate.getBlock();
                    if (block == ColorBlocks.COLOR_PORTAL.get()) {
                        ++this.portalBlockCount;
                    }

                    if (i == 0) {
                        BlockPos framePos = blockpos.offset(this.leftDir);
                        if (!this.isFrameBlock(this.world.getBlockState(framePos))) {
                            break label56;
                        }
                    } else if (i == this.width - 1) {
                        BlockPos framePos = blockpos.offset(this.rightDir);
                        if (!this.isFrameBlock(this.world.getBlockState(framePos))) {
                            break label56;
                        }
                    }
                }
            }

            for (int j = 0; j < this.width; ++j) {
                BlockPos framePos = this.bottomLeft.offset(this.rightDir, j).up(this.height);
                if (!this.isFrameBlock(this.world.getBlockState(framePos))) {
                    this.height = 0;
                    break;
                }
            }

            if (this.height <= 21 && this.height >= 3) {
                return this.height;
            } else {
                this.bottomLeft = null;
                this.width = 0;
                this.height = 0;
                return 0;
            }
        }

        private boolean isEmptyBlock(BlockState pos) {
            Block block = pos.getBlock();
            return pos.isAir() || block == ColorBlocks.COLOR_PORTAL.get();
        }

        private boolean isFrameBlock(BlockState state) {
            return ColorTags.Blocks.COLOR_PORTAL_FRAME.contains(state.getBlock());
        }

        public boolean isValid() {
            return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
        }

        public boolean canCreatePortal() {
            return this.isValid() && (this.portalBlockCount >= this.width * this.height);
        }

        public void placePortalBlocks() {
            for (int i = 0; i < this.width; ++i) {
                BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

                for (int j = 0; j < this.height; ++j) {
                    this.world.setBlockState(blockpos.up(j), ColorBlocks.COLOR_PORTAL.get().getDefaultState().with(NetherPortalBlock.AXIS, this.axis), 18);
                }
            }
        }
    }
}
