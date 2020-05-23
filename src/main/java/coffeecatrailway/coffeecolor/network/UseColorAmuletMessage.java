package coffeecatrailway.coffeecolor.network;

import coffeecatrailway.coffeecolor.common.item.ColorAmuletItem;
import coffeecatrailway.coffeecolor.curios.CuriosIntegration;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public class UseColorAmuletMessage implements IMessage<UseColorAmuletMessage> {

    @Override
    public void encode(UseColorAmuletMessage message, PacketBuffer buffer) {
    }

    @Override
    public UseColorAmuletMessage decode(PacketBuffer buffer) {
        return new UseColorAmuletMessage();
    }

    @Override
    public void handle(UseColorAmuletMessage message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayerEntity player = ctx.getSender();
            if (player != null && !CuriosIntegration.getAmuletStack(player).isEmpty()) {
                ItemStack stack = CuriosIntegration.getAmuletStack(player);
                ColorAmuletItem amulet = (ColorAmuletItem) stack.getItem();
                amulet.setActivated();
            }
        });
        ctx.setPacketHandled(true);
    }
}
