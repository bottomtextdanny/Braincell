package bottomtextdanny.braincell.mod._base.serialization.builtin;

import bottomtextdanny.braincell.Braincell;
import bottomtextdanny.braincell.mod._base.serialization.SimpleSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public final class BooleanSerializer implements SimpleSerializer<Boolean> {
    public static final ResourceLocation REF =
            new ResourceLocation(Braincell.ID, "boolean");

    @Override
    public void writeNBT(CompoundTag nbt, Boolean obj, String storage) {
        nbt.putBoolean(storage, obj);
    }

    @Nullable
    @Override
    public Boolean readNBT(CompoundTag nbt, String storage) {
        if (!nbt.contains(storage)) return null;
        return nbt.getBoolean(storage);
    }

    @Override
    public void writePacketStream(FriendlyByteBuf stream, Boolean obj) {
        stream.writeBoolean(obj);
    }

    @Override
    public Boolean readPacketStream(FriendlyByteBuf stream) {
        return stream.readBoolean();
    }

    @Override
    public ResourceLocation key() {
        return REF;
    }
}
