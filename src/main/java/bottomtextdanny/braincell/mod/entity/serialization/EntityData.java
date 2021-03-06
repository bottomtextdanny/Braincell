package bottomtextdanny.braincell.mod.entity.serialization;

import bottomtextdanny.braincell.mod._base.serialization.EntityDataSerializer;
import bottomtextdanny.braincell.mod._base.serialization.SerializerMark;
import bottomtextdanny.braincell.mod._base.serialization.SimpleSerializer;
import bottomtextdanny.braincell.mod._base.serialization.WorldDataSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

public class EntityData<T> {
    private final EntityDataReference<T> reference;
    private T objInstance;

    public EntityData(EntityDataReference<T> reference) {
        this.reference = reference;
        this.objInstance = reference.defaultProvider().get();
    }

    public static <E> EntityData<E> of(EntityDataReference<E> reference) {
        return new EntityData<>(reference);
    }

    public T get() {
        return this.objInstance;
    }

    public void set(T t) {
        this.objInstance = t;
    }

    public SerializerMark<T> getSerializer() {
        return this.reference.serializer();
    }

    public void writeToPacketStream(FriendlyByteBuf stream, Level level) {
        if (getSerializer() instanceof SimpleSerializer<T> simple) {
            simple.writePacketStream(stream, this.objInstance);
        } else if (getSerializer() instanceof WorldDataSerializer<T> worldData) {
            worldData.writePacketStream(stream, level, this.objInstance);
        } else if (getSerializer() instanceof EntityDataSerializer<T> entityData) {
            entityData.writePacketStream(stream, level, this.objInstance);
        }
    }

    public T readFromPacketStream(FriendlyByteBuf stream, Level level) {
        if (getSerializer() instanceof SimpleSerializer<T> simple) {
            set(simple.readPacketStream(stream));
        } else if (getSerializer() instanceof WorldDataSerializer<T> worldData) {
            set(worldData.readPacketStream(stream, level));
        } else if (getSerializer() instanceof EntityDataSerializer<T> entityData) {
            set(entityData.readPacketStream(stream, this.objInstance, level));
        }

        checkInvalidReadObject();

        return this.objInstance;
    }

    public void writeToNBT(CompoundTag nbt, ServerLevel level) {
        if (getSerializer() instanceof SimpleSerializer<T> simple) {
            simple.writeNBT(nbt, this.objInstance, this.reference.storageKey());
        } else if (getSerializer() instanceof WorldDataSerializer<T> worldData) {
            worldData.writeNBT(nbt, this.objInstance, level, this.reference.storageKey());
        } else if (getSerializer() instanceof EntityDataSerializer<T> entityData) {
            entityData.writeNBT(nbt, this.objInstance, level, this.reference.storageKey());
        }
    }

    public T readFromNBT(CompoundTag nbt, ServerLevel level) {
        if (getSerializer() instanceof SimpleSerializer<T> simple) {
            set(simple.readNBT(nbt, this.reference.storageKey()));
        } else if (getSerializer() instanceof WorldDataSerializer<T> worldData) {
            set(worldData.readNBT(nbt, level, this.reference.storageKey()));
        } else if (getSerializer() instanceof EntityDataSerializer<T> entityData) {
            set(entityData.readNBT(nbt, this.objInstance, level, this.reference.storageKey()));
        }

        checkInvalidReadObject();

        return this.objInstance;
    }

    private void checkInvalidReadObject() {
        if (this.objInstance == null) {
            this.objInstance = reference.defaultProvider().get();
        }
    }
}

