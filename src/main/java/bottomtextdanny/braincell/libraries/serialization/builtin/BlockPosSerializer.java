package bottomtextdanny.braincell.libraries.serialization.builtin;

import bottomtextdanny.braincell.Braincell;
import bottomtextdanny.braincell.libraries.serialization.DataSerializer;
import bottomtextdanny.braincell.libraries.serialization.SimpleDataSerializer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public final class BlockPosSerializer implements DataSerializer<BlockPos> {
    public static final ResourceLocation REF =
            new ResourceLocation(Braincell.ID, "block_pos");

    @Override
    public void writeNBT(CompoundTag nbt, BlockPos obj, String storage) {
        nbt.putLong(storage, obj.asLong());
    }

    @Nullable
    @Override
    public BlockPos readNBT(CompoundTag nbt, String storage) {
        if (!nbt.contains(storage)) return null;
        return BlockPos.of(nbt.getLong(storage));
    }

    @Override
    public void writePacketStream(FriendlyByteBuf stream, BlockPos obj) {
        stream.writeLong(obj.asLong());
    }

    @Override
    public BlockPos readPacketStream(FriendlyByteBuf stream) {
        return BlockPos.of(stream.readLong());
    }

    @Override
    public ResourceLocation key() {
        return REF;
    }
}
