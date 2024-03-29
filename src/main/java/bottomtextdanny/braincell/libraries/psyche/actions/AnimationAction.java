package bottomtextdanny.braincell.libraries.psyche.actions;

import bottomtextdanny.braincell.libraries.entity_animation.animations.Animation;
import bottomtextdanny.braincell.libraries.entity_animation.AnimationHandler;
import bottomtextdanny.braincell.libraries.entity_animation.BaseAnimatableProvider;
import bottomtextdanny.braincell.libraries.psyche.Action;
import net.minecraft.world.entity.PathfinderMob;

import java.util.function.Supplier;

public class AnimationAction<E extends PathfinderMob & BaseAnimatableProvider<?>, A extends Animation<?>> extends Action<E> {
    protected final Supplier<A> animationProvider;
    protected A animation;
    protected final AnimationHandler animationHandler;

    public AnimationAction(E mob, A animation, AnimationHandler animationModule) {
        super(mob);
        this.animationProvider = () -> animation;
        this.animationHandler = animationModule;
    }

    public AnimationAction(E mob, Supplier<A> animation, AnimationHandler animationModule) {
        super(mob);
        this.animationProvider = animation;
        this.animationHandler = animationModule;
    }

    @Override
    public boolean canStart() {
        return active();
    }

    @Override
    protected void start() {
        super.start();
        this.animationHandler.play(mob, this.animation = this.animationProvider.get());
    }

    @Override
    public boolean shouldKeepGoing() {
        return active() && this.animationHandler.isPlaying(this.animation);
    }
}
