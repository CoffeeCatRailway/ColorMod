package coffeecatrailway.coffeecolor.network;

import coffeecatrailway.coffeecolor.common.item.ColorArtifactItem;
import coffeecatrailway.coffeecolor.integration.curios.CuriosIntegration;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public class UseColorArtifactMessage implements IMessage<UseColorArtifactMessage> {

    @Override
    public void encode(UseColorArtifactMessage message, PacketBuffer buffer) {
    }

    @Override
    public UseColorArtifactMessage decode(PacketBuffer buffer) {
        return new UseColorArtifactMessage();
    }

    @Override
    public void handle(UseColorArtifactMessage message, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayerEntity player = ctx.getSender();
            if (player != null)
                CuriosIntegration.getArtifactStack(player).stream().filter(stack -> !stack.isEmpty()).forEach(stack -> ((ColorArtifactItem) stack.getItem()).setActivated());
        });
        ctx.setPacketHandled(true);
    }
}
