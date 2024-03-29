package bottomtextdanny.braincell.libraries.registry.item_extensions;

import net.minecraft.client.resources.model.ModelResourceLocation;

public record ExtraModelData(int index, ModelResourceLocation location) {

    public static ExtraModelData of(Enum<?> entry, ModelResourceLocation location) {
        return new ExtraModelData(entry.ordinal(), location);
    }

    public static ExtraModelData of(int index, ModelResourceLocation location) {
        return new ExtraModelData(index, location);
    }
}
