package coffeecatrailway.coffeecolor.common.block;

import coffeecatrailway.coffeecolor.common.IHasColor;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 23/05/2020
 */
public class ColorGemOreBlock extends OreBlock implements IHasColor {

    private DyeColor color;

    public ColorGemOreBlock(Properties properties, DyeColor color) {
        super(properties);
        this.color = color;
    }

    @Override
    public int getColor(ItemStack stack, int tintindex) {
        return this.color.getColorValue();
    }

    @Override
    protected int getExperience(Random rand) {
        return MathHelper.nextInt(rand, 3, 7);
    }
}
