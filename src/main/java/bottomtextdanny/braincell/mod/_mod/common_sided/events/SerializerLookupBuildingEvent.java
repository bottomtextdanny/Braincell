package bottomtextdanny.braincell.mod._mod.common_sided.events;

import bottomtextdanny.braincell.mod._base.serialization.SerializerMark;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.Event;
import org.apache.commons.lang3.mutable.MutableInt;

public final class SerializerLookupBuildingEvent extends Event {
    private final ImmutableMap.Builder<ResourceLocation, SerializerMark<?>> unbuiltSerializerLookup;
    private final ImmutableBiMap.Builder<Integer, SerializerMark<?>> unbuiltIdLookupBuilder;
    private final MutableInt idCounter;

    public SerializerLookupBuildingEvent(
            ImmutableMap.Builder<ResourceLocation, SerializerMark<?>> serializerLookup,
            ImmutableBiMap.Builder<Integer, SerializerMark<?>> idLookupBuilder,
            MutableInt idCounter) {
        this.unbuiltSerializerLookup = serializerLookup;
        this.unbuiltIdLookupBuilder = idLookupBuilder;
        this.idCounter = idCounter;
    }

    public void addSerializer(SerializerMark<?> mark) {
        this.unbuiltSerializerLookup.put(mark.key(), mark);
        this.unbuiltIdLookupBuilder.put(this.idCounter.getAndIncrement(), mark);
    }
}
