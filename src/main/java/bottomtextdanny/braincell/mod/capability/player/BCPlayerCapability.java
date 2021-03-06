package bottomtextdanny.braincell.mod.capability.player;

import bottomtextdanny.braincell.mod.capability.CapabilityModule;
import bottomtextdanny.braincell.mod.capability.CapabilityWrap;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class BCPlayerCapability extends CapabilityWrap<BCPlayerCapability, Player> {
    public static Capability<BCPlayerCapability> TOKEN = CapabilityManager.get(new CapabilityToken<>(){});
    private BCAccessoryModule accessories;

    public BCPlayerCapability(Player holder) {
        super(holder);
    }

    @Override
    protected void populateModuleList(ImmutableList.Builder<CapabilityModule<Player, BCPlayerCapability>> moduleList) {
        this.accessories = new BCAccessoryModule(this);
        moduleList.add(this.accessories);
    }

    public BCAccessoryModule getAccessories() {
        return accessories;
    }

    @Override
    protected Capability<?> getToken() {
        return TOKEN;
    }
}
