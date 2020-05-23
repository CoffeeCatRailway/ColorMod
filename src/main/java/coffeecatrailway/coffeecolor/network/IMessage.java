package coffeecatrailway.coffeecolor.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public interface IMessage<T> {

    void encode(T message, PacketBuffer buffer);

    T decode(PacketBuffer buffer);

    void handle(T message, Supplier<NetworkEvent.Context> supplier);
}
