package coffeecatrailway.coffeecolor.registry;

import coffeecatrailway.coffeecolor.ColorMod;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 14/05/2020
 */
public class ColorTags {

    @Nullable
    public static Tag<Item> getLogTagFromBlock(Tag<Block> blockTag) {
        if (Items.LOGS.containsKey(blockTag.getId()))
            return Items.LOGS.get(blockTag.getId());
        return null;
    }

    public static class Blocks implements NonNullConsumer<RegistrateTagsProvider<Block>> {

        public static final ColorTags.Blocks INSTANCE = new ColorTags.Blocks();
        static final Map<ResourceLocation, Tag<Block>> LOGS = new HashMap<>();

        @Override
        public void accept(RegistrateTagsProvider<Block> provider) {
            provider.getBuilder(BlockTags.LOGS).add(LOGS.values().toArray(new Tag[]{})).build(BlockTags.LOGS.getId());
        }

        public static final Tag<Block> WHITE_LOGS = tagLog("white_logs");
        public static final Tag<Block> ORANGE_LOGS = tagLog("orange_logs");
        public static final Tag<Block> MAGENTA_LOGS = tagLog("magenta_logs");
        public static final Tag<Block> LIGHT_BLUE_LOGS = tagLog("light_blue_logs");
        public static final Tag<Block> YELLOW_LOGS = tagLog("yellow_logs");
        public static final Tag<Block> LIME_LOGS = tagLog("lime_logs");
        public static final Tag<Block> PINK_LOGS = tagLog("pink_logs");
        public static final Tag<Block> GRAY_LOGS = tagLog("gray_logs");
        public static final Tag<Block> LIGHT_GRAY_LOGS = tagLog("light_gray_logs");
        public static final Tag<Block> CYAN_LOGS = tagLog("cyan_logs");
        public static final Tag<Block> PURPLE_LOGS = tagLog("purple_logs");
        public static final Tag<Block> BLUE_LOGS = tagLog("blue_logs");
        public static final Tag<Block> BROWN_LOGS = tagLog("brown_logs");
        public static final Tag<Block> GREEN_LOGS = tagLog("green_logs");
        public static final Tag<Block> RED_LOGS = tagLog("red_logs");
        public static final Tag<Block> BLACK_LOGS = tagLog("black_logs");

        static Tag<Block> tagLog(String name) {
            Tag<Block> tag = tag(name);
            LOGS.put(tag.getId(), tag);
            return tag;
        }

        static Tag<Block> tag(String name) {
            return new BlockTags.Wrapper(ColorMod.getLocation(name));
        }
    }

    public static class Items implements NonNullConsumer<RegistrateTagsProvider<Item>> {

        public static final ColorTags.Items INSTANCE = new ColorTags.Items();
        static final Map<ResourceLocation, Tag<Item>> LOGS = new HashMap<>();

        @Override
        public void accept(RegistrateTagsProvider<Item> provider) {
            provider.getBuilder(ItemTags.LOGS).add(LOGS.values().toArray(new Tag[]{})).build(ItemTags.LOGS.getId());
            LOGS.values().forEach(tag -> provider.getBuilder(tag).build(tag.getId()));
        }

        public static final Tag<Item> WHITE_LOGS = tagLog("white_logs");
        public static final Tag<Item> ORANGE_LOGS = tagLog("orange_logs");
        public static final Tag<Item> MAGENTA_LOGS = tagLog("magenta_logs");
        public static final Tag<Item> LIGHT_BLUE_LOGS = tagLog("light_blue_logs");
        public static final Tag<Item> YELLOW_LOGS = tagLog("yellow_logs");
        public static final Tag<Item> LIME_LOGS = tagLog("lime_logs");
        public static final Tag<Item> PINK_LOGS = tagLog("pink_logs");
        public static final Tag<Item> GRAY_LOGS = tagLog("gray_logs");
        public static final Tag<Item> LIGHT_GRAY_LOGS = tagLog("light_gray_logs");
        public static final Tag<Item> CYAN_LOGS = tagLog("cyan_logs");
        public static final Tag<Item> PURPLE_LOGS = tagLog("purple_logs");
        public static final Tag<Item> BLUE_LOGS = tagLog("blue_logs");
        public static final Tag<Item> BROWN_LOGS = tagLog("brown_logs");
        public static final Tag<Item> GREEN_LOGS = tagLog("green_logs");
        public static final Tag<Item> RED_LOGS = tagLog("red_logs");
        public static final Tag<Item> BLACK_LOGS = tagLog("black_logs");

        static Tag<Item> tagLog(String name) {
            Tag<Item> tag = tag(name);
            LOGS.put(tag.getId(), tag);
            return tag;
        }

        static Tag<Item> tag(String name) {
            return new ItemTags.Wrapper(ColorMod.getLocation(name));
        }
    }
}
