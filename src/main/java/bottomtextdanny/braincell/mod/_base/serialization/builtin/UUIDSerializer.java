package bottomtextdanny.braincell.mod._base.serialization.builtin;

import bottomtextdanny.braincell.Braincell;
import bottomtextdanny.braincell.mod._base.serialization.SimpleSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.UUID;

public final class UUIDSerializer implements SimpleSerializer<UUID> {
    public static final ResourceLocation REF =
            new ResourceLocation(Braincell.ID, "uuid");
    
    @Override
    public void writeNBT(CompoundTag nbt, UUID obj, String storage) {
        nbt.putUUID(storage, obj);
    }

    @Nullable
    @Override
    public UUID readNBT(CompoundTag nbt, String storage) {
        if (!nbt.contains(storage)) return null;
        return nbt.getUUID(storage);
    }

    @Override
    public void writePacketStream(FriendlyByteBuf stream, UUID obj) {
        stream.writeUUID(obj);
    }

    @Override
    public UUID readPacketStream(FriendlyByteBuf stream) {
        return stream.readUUID();
    }

    @Override
    public ResourceLocation key() {
        return REF;
    }
}
