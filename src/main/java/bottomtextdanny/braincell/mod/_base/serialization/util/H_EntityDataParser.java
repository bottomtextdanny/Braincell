package bottomtextdanny.braincell.mod._base.serialization.util;

import bottomtextdanny.braincell.mod._base.serialization.EntityDataSerializer;
import bottomtextdanny.braincell.mod._base.serialization.SerializerMark;
import bottomtextdanny.braincell.mod._base.serialization.SimpleSerializer;
import bottomtextdanny.braincell.mod._base.serialization.WorldDataSerializer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public final class H_EntityDataParser {

    public static <T> void writeDataToPacket(
            FriendlyByteBuf stream,
            SerializerMark<T> serializer,
            T object, 
            Level level) {
        if (serializer instanceof SimpleSerializer<T> simple) {
            simple.writePacketStream(stream, object);
        } else if (serializer instanceof WorldDataSerializer<T> worldData) {
            worldData.writePacketStream(stream, level, object);
        } else if (serializer instanceof EntityDataSerializer<T> entityData) {
            entityData.writePacketStream(stream, level, object);
        } else {
            throw writeUnsupportedSerializerException(serializer.getClass(), serializer.key());
        }
    }

    public static <T> T readDataFromPacket(
            FriendlyByteBuf stream,
            SerializerMark<T> serializer,
            T baseObj,
            Level level) {
        if (serializer instanceof SimpleSerializer<T> simple) {
            return simple.readPacketStream(stream);
        } else if (serializer instanceof WorldDataSerializer<T> worldData) {
            return worldData.readPacketStream(stream, level);
        } else if (serializer instanceof EntityDataSerializer<T> entityData) {
            return entityData.readPacketStream(stream, baseObj, level);
        } else {
            throw readUnsupportedSerializerException(serializer.getClass(), serializer.key());
        }
    }

    public static <T> void writeDataToNBT(
            CompoundTag nbt,
            SerializerMark<T> serializer,
            String storageKey,
            T object, 
            ServerLevel level) {
        if (serializer instanceof SimpleSerializer<T> simple) {
            simple.writeNBT(nbt, object, storageKey);
        } else if (serializer instanceof WorldDataSerializer<T> worldData) {
            worldData.writeNBT(nbt, object, level, storageKey);
        } else {
            throw writeUnsupportedSerializerException(serializer.getClass(), serializer.key());
        }
    }

    @Nullable
    public static <T> T readDataFromNBT(
            CompoundTag nbt,
            SerializerMark<T> serializer,
            T baseObj,
            String storageKey,
            ServerLevel level) {
        if (serializer instanceof SimpleSerializer<T> simple) {
            return simple.readNBT(nbt, storageKey);
        } else if (serializer instanceof WorldDataSerializer<T> worldData) {
            return worldData.readNBT(nbt, level, storageKey);
        } else if (serializer instanceof EntityDataSerializer<T> entityData) {
            return entityData.readNBT(nbt, baseObj, level, storageKey);
        } else {
            throw readUnsupportedSerializerException(serializer.getClass(), serializer.key());
        }
    }

    public static UnsupportedOperationException readUnsupportedSerializerException(
            Class<? extends SerializerMark> serializerClass,
            ResourceLocation serializerKey) {
        String message = new StringBuilder("Parser can only read entity image, world image and simple image serializers, tried to read: ")
                .append(serializerClass.getSimpleName())
                .append(", for serializer: ")
                .append(serializerKey.toString())
                .append('.')
                .toString();
        return new UnsupportedOperationException(message);
    }

    public static UnsupportedOperationException writeUnsupportedSerializerException(
            Class<? extends SerializerMark> serializerClass, 
            ResourceLocation serializerKey) {
        String message = new StringBuilder("Parser can only write from entity image, world image and simple image serializers, tried to write from: ")
                .append(serializerClass.getSimpleName())
                .append(", for serializer: ")
                .append(serializerKey.toString())
                .append('.')
                .toString();
        return new UnsupportedOperationException(message);
    }
}
