package coffeecatrailway.coffeecolor.integration.curios;

import coffeecatrailway.coffeecolor.ColorMod;
import coffeecatrailway.coffeecolor.common.item.ColorArtifactItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.CurioType;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;
import top.theillusivec4.curios.api.capability.ICurioItemHandler;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 22/05/2020
 */
public class CuriosIntegration {

    public static CuriosIntegration INSTANCE;

    public static void init() {
        INSTANCE = new CuriosIntegration();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CuriosIntegration::sendImc);
    }

    public static void sendImc(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("necklace"));
        InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("ring"));

        ColorMod.LOGGER.info("Common Event: Register curios types");
    }

    public static List<ItemStack> getArtifactStack(PlayerEntity player) {
        List<AtomicReference<ItemStack>> artifacts = new ArrayList<>();
        artifacts.add(new AtomicReference<>(ItemStack.EMPTY));
        artifacts.add(new AtomicReference<>(ItemStack.EMPTY));

        LazyOptional<ICurioItemHandler> optional = CuriosAPI.getCuriosHandler(player);
        optional.ifPresent(handler -> {
            if (handler.getStackInSlot("necklace", 0).getItem() instanceof ColorArtifactItem)
                artifacts.get(0).set(handler.getStackInSlot("necklace", 0));
            if (CuriosAPI.getType("ring").isPresent()) {
                for (int i = 0; i < CuriosAPI.getType("ring").get().getSize(); i++) {
                    if (handler.getStackInSlot("ring", i).getItem() instanceof ColorArtifactItem)
                        artifacts.get(1).set(handler.getStackInSlot("ring", i));
                }
            }
        });

        return artifacts.stream().map(AtomicReference::get).collect(Collectors.toList());
    }

    public ICapabilityProvider initCap(ItemStack stack) {
        return new ICapabilityProvider() {

            private final LazyOptional<ICurio> opt = LazyOptional.of(() -> new CurioWrapper(stack));

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                return CuriosCapability.ITEM.orEmpty(cap, opt);
            }
        };
    }
}
