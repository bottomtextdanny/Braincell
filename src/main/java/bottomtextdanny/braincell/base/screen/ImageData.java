package bottomtextdanny.braincell.base.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImageData extends ResourceLocation implements ImageBounds {
    private final int width;
    private final int height;

    protected ImageData(String[] data, int width, int height) {
        super(data);
        this.width = width;
        this.height = height;
    }

    public ImageData(String key, int width, int height) {
        super(key);
        this.width = width;
        this.height = height;
    }

    public ImageData(String namespace, String path, int width, int height) {
        super(namespace, path);
        this.width = width;
        this.height = height;
    }

    public void use() {
        RenderSystem.setShaderTexture(0, this);
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }
}
