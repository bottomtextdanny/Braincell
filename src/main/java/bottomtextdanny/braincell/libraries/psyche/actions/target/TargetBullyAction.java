package bottomtextdanny.braincell.libraries.psyche.actions.target;

import bottomtextdanny.braincell.libraries.psyche.targeting.MobMatchPredicate;
import bottomtextdanny.braincell.libraries.psyche.input.ActionInputKey;
import bottomtextdanny.braincell.libraries.psyche.actions.ConstantThoughtAction;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class TargetBullyAction extends ConstantThoughtAction<PathfinderMob> {
    private final MobMatchPredicate<LivingEntity> targetParameters;
    @Nullable
    private BiConsumer<Mob, LivingEntity> findTargetCallOut;
    @Nullable
    private LivingEntity localTarget;
    private int timestamp;

    public TargetBullyAction(PathfinderMob mob, MobMatchPredicate<? super LivingEntity> targetParameters) {
        super(mob, null);
        this.targetParameters = targetParameters.cast();
    }

    public TargetBullyAction findTargetCallOut(BiConsumer<Mob, LivingEntity> callOut) {
        this.findTargetCallOut = callOut;
        return this;
    }

    @Override
    protected void update() {
        if (!active()) return;
        if (!this.mob.isWithinRestriction()) return;

        LivingEntity previousState = this.localTarget;

        if (this.mob.getLastHurtByMob() == null || !this.mob.getLastHurtByMob().isAlive()) {
            this.localTarget = null;
        } else if (this.timestamp != this.mob.getLastHurtByMobTimestamp()) {
            if (this.targetParameters.test(this.mob, this.mob.getLastHurtByMob()) && EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(this.mob)) {

                this.mob.setTarget(this.mob.getLastHurtByMob());
                this.localTarget = this.mob.getTarget();
            }
            this.timestamp = this.mob.getLastHurtByMobTimestamp();
        }

        if (this.localTarget != null && this.localTarget.isAlive() && previousState == null) {
            Runnable newTargetInput = getPsyche().getInputs().get(ActionInputKey.SET_TARGET_CALL);
            if (newTargetInput != null) newTargetInput.run();
            if (this.findTargetCallOut != null)
                this.findTargetCallOut.accept(this.mob, this.localTarget);
        }
    }

    @Override
    public boolean cancelNext() {
        return this.localTarget != null && this.mob.getTarget() == this.localTarget;
    }
}
