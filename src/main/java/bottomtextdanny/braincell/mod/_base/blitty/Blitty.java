package bottomtextdanny.braincell.mod._base.blitty;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import bottomtextdanny.braincell.base.screen.ImageBounds;
import bottomtextdanny.braincell.mod._base.blitty.renderer.BlittyRenderer;
import net.minecraft.client.renderer.GameRenderer;

public record Blitty(ImageBounds image, int x, int y, int width, int height) {

    public void render(PoseStack matrixStack, float posX, float posY, float posZ) {
        Matrix4f matrix = matrixStack.last().pose();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        float x0 = this.x / (float)this.image.width();
        float y0 = this.y / (float)this.image.height();
        float x1 = (this.x + this.width) / (float)this.image.width();
        float y1 = (this.y + this.height) / (float)this.image.height();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix, posX, posY + this.height, posZ).uv(x0, y1).endVertex();
        bufferbuilder.vertex(matrix, posX + this.width, posY + this.height, posZ).uv(x1, y1).endVertex();
        bufferbuilder.vertex(matrix, posX + this.width, posY, posZ).uv(x1, y0).endVertex();
        bufferbuilder.vertex(matrix, posX, posY, posZ).uv(x0, y0).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
    }

    public void render(PoseStack matrixStack, float posX, float posY, float posZ, BlittyRenderer renderer, BlittyConfig... configurations) {
        renderer.render(this, matrixStack, posX, posY, posZ, configurations);
    }
}
