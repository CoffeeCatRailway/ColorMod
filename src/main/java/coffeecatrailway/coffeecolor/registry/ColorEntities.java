package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.common.ColorMonsterEntity;
import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 18/05/2020
 */
public class ColorEntities {

    public static List<RegistryEntry<? extends LazySpawnEggItem<?>>> SPAWN_EGGS = new ArrayList<>();

    public static final RegistryEntry<EntityType<ColorMonsterEntity>> COLOR_MONSTER = REGISTRATE.entity("color_monster", ColorMonsterEntity::new, EntityClassification.MONSTER)
            .loot((lootTables, entity) -> lootTables.registerLootTable(entity, LootTable.builder())).defaultLang().properties(prop -> prop.size(0.5f, 0.85f)).register();

    static {
        SPAWN_EGGS.add(REGISTRATE.item("color_monster_spawn_egg", spawnEgg(ColorEntities.COLOR_MONSTER, DyeColor.ORANGE.getColorValue(), DyeColor.CYAN.getColorValue())).properties(prop -> prop.group(ItemGroup.MISC))
                .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    }

    private static <E extends Entity> NonNullFunction<Item.Properties, LazySpawnEggItem<E>> spawnEgg(RegistryEntry<EntityType<E>> entity, int color1, int color2) {
        return prop -> new LazySpawnEggItem<>(entity, color1, color2, prop);
    }

    public static void registerPlacements(RegistryEvent.Register<EntityType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName())) return;

        EntitySpawnPlacementRegistry.register(COLOR_MONSTER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        Map map = ObfuscationReflectionHelper.getPrivateValue(EntitySpawnPlacementRegistry.class, null, "field_209347_a");
        if (map != null) {
            map.remove(EntityType.SHEEP);
            EntitySpawnPlacementRegistry.register(EntityType.SHEEP, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, reason, pos, random) ->
                    AnimalEntity.canAnimalSpawn(type, world, reason, pos, random)
                            || world.getBlockState(pos.down()).getBlock() == ColorBlocks.COLOR_GRASS_BLOCK.get() && world.getLightSubtracted(pos, 0) > 8);
        }
    }

    public static void load() {
    }
}
