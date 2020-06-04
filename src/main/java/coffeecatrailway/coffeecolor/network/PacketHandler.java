package coffeecatrailway.coffeecolor.network;

import coffeecatrailway.coffeecolor.ColorMod;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    private static int nextId = 0;
    public static SimpleChannel INSTANCE;

    public static void init() {
        INSTANCE = NetworkRegistry.ChannelBuilder.named(ColorMod.getLocation("network"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();
        register(UseColorArtifactMessage.class, new UseColorArtifactMessage());

        ColorMod.LOGGER.info("Common Event: Register packets");
    }

    private static <T> void register(Class<T> clazz, IMessage<T> message) {
        INSTANCE.registerMessage(nextId++, clazz, message::encode, message::decode, message::handle);
    }
}
