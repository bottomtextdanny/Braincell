package bottomtextdanny.braincell.libraries.entity_animation.animations;

import bottomtextdanny.braincell.libraries.entity_animation.AnimationHandler;

import java.util.function.Supplier;

public class LoopedAnimation extends AbstractAnimation<LoopedAnimationData> {

    public LoopedAnimation(int duration) {
        super(duration);
    }

    @Override
    public Supplier<LoopedAnimationData> dataForPlay() {
        return LoopedAnimationData::new;
    }

    @Override
    public int progressTick(int progress, AnimationHandler handler) {
        return (progress + 1) % getDuration();
    }
	
	@Override
	public boolean goal(int progression, AnimationHandler handler) {
		return getData(handler).stop;
	}
	
	public void setStop(AnimationHandler handler, boolean pass) {
        getData(handler).stop = pass;
    }
}
