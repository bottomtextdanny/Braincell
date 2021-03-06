package bottomtextdanny.braincell.mod.serialization.entity;

import bottomtextdanny.braincell.base.scheduler.IntScheduler;
import bottomtextdanny.braincell.Braincell;
import bottomtextdanny.braincell.mod._base.serialization.EntityDataSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public final class VariableIntSchedulerSerializer implements EntityDataSerializer<IntScheduler.Variable> {
    public static final ResourceLocation REF = new ResourceLocation(Braincell.ID, "variable_int_scheduler");

    @Override
    public void writeNBT(CompoundTag nbt, IntScheduler.Variable obj, ServerLevel level, String storage) {
        nbt.putInt(storage + "_bound", obj.bound());
        nbt.putInt(storage, obj.current());
    }

    @Nullable
    @Override
    public IntScheduler.Variable readNBT(CompoundTag nbt, IntScheduler.Variable baseObj, ServerLevel level, String storage) {
        if (!nbt.contains(storage)) return null;
        return new IntScheduler.Variable(
                baseObj.getNextBoundSupplier(),
                nbt.getInt(storage + "_bound"),
                nbt.getInt(storage));
    }

    @Override
    public void writePacketStream(FriendlyByteBuf stream, Level level, IntScheduler.Variable obj) {
        stream.writeInt(obj.bound());
        stream.writeInt(obj.current());
    }

    @Override
    public IntScheduler.Variable readPacketStream(FriendlyByteBuf stream, IntScheduler.Variable baseObj, Level level) {
        return new IntScheduler.Variable(
                baseObj.getNextBoundSupplier(),
                stream.readInt(),
                stream.readInt());
    }

    @Override
    public ResourceLocation key() {
        return REF;
    }
}
