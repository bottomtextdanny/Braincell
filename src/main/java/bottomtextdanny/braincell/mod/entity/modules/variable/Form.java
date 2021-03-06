package bottomtextdanny.braincell.mod.entity.modules.variable;

import bottomtextdanny.braincell.mod._mod.client_sided.variant_data.VariantRenderingData;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public abstract class Form<E extends LivingEntity> {
    @OnlyIn(Dist.CLIENT)
    private VariantRenderingData<E> rendering;

    public Form() {
        super();
    }

    protected abstract VariantRenderingData<E> createRenderingHandler();

    @SuppressWarnings("unchecked")
    public final void applyAttributeBonusesRaw(Mob entityIn) {
        applyAttributeBonuses((E) entityIn);
    }

    public void applyAttributeBonuses(E entityIn) {}

    @Nullable
    public EntityDimensions boxSize() {
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public VariantRenderingData<E> getRendering() {
        if (this.rendering == null)
            this.rendering = createRenderingHandler();
        return this.rendering;
    }
}
