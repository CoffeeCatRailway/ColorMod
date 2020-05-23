package coffeecatrailway.coffeecolor.registrate;

import coffeecatrailway.coffeecolor.ColorMod;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.HashMap;
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

            provider.getBuilder(ColorTags.Blocks.COLOR_PORTAL_FRAME)
                    .add(BlockTags.WOOL)
                    .add(net.minecraft.block.Blocks.WHITE_CONCRETE, net.minecraft.block.Blocks.WHITE_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.ORANGE_CONCRETE, net.minecraft.block.Blocks.ORANGE_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.MAGENTA_CONCRETE, net.minecraft.block.Blocks.MAGENTA_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.LIGHT_BLUE_CONCRETE, net.minecraft.block.Blocks.LIGHT_BLUE_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.YELLOW_CONCRETE, net.minecraft.block.Blocks.YELLOW_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.LIME_CONCRETE, net.minecraft.block.Blocks.LIME_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.PINK_CONCRETE, net.minecraft.block.Blocks.PINK_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.GRAY_CONCRETE, net.minecraft.block.Blocks.GRAY_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.LIGHT_GRAY_CONCRETE, net.minecraft.block.Blocks.LIGHT_GRAY_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.CYAN_CONCRETE, net.minecraft.block.Blocks.CYAN_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.PURPLE_CONCRETE, net.minecraft.block.Blocks.PURPLE_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.BLUE_CONCRETE, net.minecraft.block.Blocks.BLUE_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.BROWN_CONCRETE, net.minecraft.block.Blocks.BROWN_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.GREEN_CONCRETE, net.minecraft.block.Blocks.GREEN_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.RED_CONCRETE, net.minecraft.block.Blocks.RED_CONCRETE_POWDER)
                    .add(net.minecraft.block.Blocks.BLACK_CONCRETE, net.minecraft.block.Blocks.BLACK_CONCRETE_POWDER)

                    .add(net.minecraft.block.Blocks.WHITE_TERRACOTTA, net.minecraft.block.Blocks.WHITE_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.ORANGE_TERRACOTTA, net.minecraft.block.Blocks.ORANGE_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.MAGENTA_TERRACOTTA, net.minecraft.block.Blocks.MAGENTA_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.LIGHT_BLUE_TERRACOTTA, net.minecraft.block.Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.YELLOW_TERRACOTTA, net.minecraft.block.Blocks.YELLOW_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.LIME_TERRACOTTA, net.minecraft.block.Blocks.LIME_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.PINK_TERRACOTTA, net.minecraft.block.Blocks.PINK_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.GRAY_TERRACOTTA, net.minecraft.block.Blocks.GRAY_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.LIGHT_GRAY_TERRACOTTA, net.minecraft.block.Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.CYAN_TERRACOTTA, net.minecraft.block.Blocks.CYAN_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.PURPLE_TERRACOTTA, net.minecraft.block.Blocks.PURPLE_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.BLUE_TERRACOTTA, net.minecraft.block.Blocks.BLUE_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.BROWN_TERRACOTTA, net.minecraft.block.Blocks.BROWN_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.GREEN_TERRACOTTA, net.minecraft.block.Blocks.GREEN_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.RED_TERRACOTTA, net.minecraft.block.Blocks.RED_GLAZED_TERRACOTTA)
                    .add(net.minecraft.block.Blocks.BLACK_TERRACOTTA, net.minecraft.block.Blocks.BLACK_GLAZED_TERRACOTTA)
                    .build(ColorTags.Blocks.COLOR_PORTAL_FRAME.getId());

            ColorMod.LOGGER.info("DataGen: Block tags");
        }

        public static final Tag<Block> COLOR_PORTAL_FRAME = tag("color_portal_frame");

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

            provider.getBuilder(Items.CURIOS_NECKLACE).build(Items.CURIOS_NECKLACE.getId());
            provider.getBuilder(Tags.Items.GEMS).add(Items.COLOR_GEMS).build(Tags.Items.GEMS.getId());

            ColorMod.LOGGER.info("DataGen: Item tags");
        }

        public static final Tag<Item> CURIOS_NECKLACE = tag("curios", "necklace");
        public static final Tag<Item> COLOR_GEMS = tag("color_gems");

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

        static Tag<Item> tag(String id, String name) {
            return new ItemTags.Wrapper(new ResourceLocation(id, name));
        }
    }
}
