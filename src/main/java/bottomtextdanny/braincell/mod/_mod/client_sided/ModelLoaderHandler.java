package bottomtextdanny.braincell.mod._mod.client_sided;

import com.google.common.collect.Lists;
import bottomtextdanny.braincell.mod._base.registry.item_extensions.ExtraItemModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

public final class ModelLoaderHandler {
    private final List<ExtraItemModelLoader> extraModelLoaders = Lists.newLinkedList();

    public ModelLoaderHandler() {
        super();
    }

    public void sendListeners() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerExtraModelsHook);
    }

    public boolean addModelLoader(ExtraItemModelLoader itemModelLoader) {
        return this.extraModelLoaders.add(itemModelLoader);
    }

    private void registerExtraModelsHook(ModelRegistryEvent event) {
        this.extraModelLoaders.forEach(loader -> {
            loader.bake(event);
        });
    }
}
