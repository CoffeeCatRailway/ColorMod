package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.common.block.ColorGrassBlock;
import coffeecatrailway.coffeecolor.common.block.ColorPortalBlock;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

import static coffeecatrailway.coffeecolor.ColorMod.REGISTRATE;

/**
 * @author CoffeeCatRailway
 * Created: 11/05/2020
 */
public class ColorBlocks {

    public static final RegistryEntry<Block> COLOR_DIRT = REGISTRATE.object("color_dirt").block(Block::new)
            .defaultBlockstate().defaultLoot().defaultLang()
            .initialProperties(Material.EARTH, MaterialColor.DIRT).properties(prop -> prop.hardnessAndResistance(0.5F).sound(SoundType.GROUND)).simpleItem().register();

    public static final RegistryEntry<ColorGrassBlock> COLOR_GRASS_BLOCK = REGISTRATE.object("color_grass_block").block(ColorGrassBlock::new)
            .blockstate(NonNullBiConsumer.noop()).defaultLang()
            .loot((lootTables, block) -> lootTables.registerLootTable(block, RegistrateBlockLootTables.droppingWithSilkTouch(block, COLOR_DIRT.get())))
            .initialProperties(Material.ORGANIC, MaterialColor.GRASS).properties(prop -> prop.tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)).simpleItem().register();

    public static final RegistryEntry<ColorPortalBlock> COLOR_PORTAL = REGISTRATE.block("color_portal", ColorPortalBlock::new)
            .blockstate(NonNullBiConsumer.noop()).defaultLang()
            .initialProperties(Material.PORTAL, Material.PORTAL.getColor())
            .properties(prop -> prop.doesNotBlockMovement().harvestTool(ToolType.PICKAXE).hardnessAndResistance(-1.0F, 3000000.0F).lightValue(10).sound(SoundType.GLASS).notSolid())
            .register();

    public static void load() {
    }
}
