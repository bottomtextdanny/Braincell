package bottomtextdanny.braincell.mod.world.helpers;

import bottomtextdanny.braincell.base.vector.DistanceCalc2;
import bottomtextdanny.braincell.base.vector.DistanceCalc3;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public final class ReachHelper {

    public static Predicate<LivingEntity> isEntityCloseToAnother(PathfinderMob opinionated, LivingEntity target, float distance) {
        return livingEntity -> reachSqr(opinionated, target) < distance;
    }

    public static float horizontalReachSqr(PathfinderMob opinionated, Entity target) {
        float xDist = (float)(opinionated.getX() - target.getX());
        float zDist = (float)(opinionated.getZ() - target.getZ());
        float distance = xDist * xDist + zDist * zDist;
        return (float) Math.max(distance - target.getBbWidth() / 2.0, 0.0);
    }

    public static float horizontalReach(PathfinderMob opinionated, Entity target) {
        float xDist = (float)(opinionated.getX() - target.getX());
        float zDist = (float)(opinionated.getZ() - target.getZ());
        float distance = (float) Math.sqrt(xDist * xDist + zDist * zDist);
        return (float) Math.max(distance - target.getBbWidth() / 2.0, 0.0);
    }

    public static float reachSqr(PathfinderMob opinionated, Entity target) {
        return (float) Math.max(opinionated.distanceTo(target) - target.getBbWidth() / 2.0, 0.0);
    }

    public static float reach2(Entity entity1, Entity entity2, DistanceCalc2 calculator) {
        return (float) Math.max(calculator.distance(entity1.getX(), entity1.getZ(), entity2.getX(), entity2.getZ()), 0.0);
    }

    public static float reach2(Entity entity1, Entity entity2, BiFunction<Entity, Entity, DistanceCalc2> calculator) {
        return (float) Math.max(calculator.apply(entity1, entity2)
                .distance(entity1.getX(), entity1.getZ(), entity2.getX(), entity2.getZ()), 0.0);
    }

    public static float reach3(Entity entity1, Entity entity2, DistanceCalc3 calculator) {
        return (float) Math.max(calculator.distance(entity1.position(), entity2.position()), 0.0);
    }

    public static float reach3(Entity entity1, Entity entity2, BiFunction<Entity, Entity, DistanceCalc3> calculator) {
        return (float) Math.max(calculator.apply(entity1, entity2).distance(entity1.position(), entity2.position()), 0.0);
    }

    public static DistanceCalc3 euclideanReach3(double xBS, double yBS, double zBS) {
        return (x1, y1, z1, x2, y2, z2) -> {
            x1 = Math.max(Math.abs(x1 - x2) - xBS, 0.0);
            y1 = Math.max(Math.abs(y1 - y2) - yBS, 0.0);
            z1 = Math.max(Math.abs(z1 - z2) - zBS, 0.0);
            return Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
        };
    }

    public static DistanceCalc3 euclideanEntityReach3(Entity entity1, Entity entity2) {
        return (x1, y1, z1, x2, y2, z2) -> {
            float w = entity1.getBbWidth() / 2.0F + entity2.getBbWidth() / 2.0F;
            float h = entity1.getBbHeight() / 2.0F + entity2.getBbHeight() / 2.0F;

            x1 = Math.max(Math.abs(x1 - x2) - w, 0.0);
            y1 = Math.max(Math.abs(y1 - y2 + h) - h, 0.0);
            z1 = Math.max(Math.abs(z1 - z2) - w, 0.0);
            return Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
        };
    }

    public static DistanceCalc2 euclideanReach2(double xBS, double yBS) {
        return (x1, y1, x2, y2) -> {
            x1 = Math.max(Math.abs(x1 - x2) - xBS, 0.0);
            y1 = Math.max(Math.abs(y1 - y2) - yBS, 0.0);
            return Math.sqrt(x1 * x1 + y1 * y1);
        };
    }

    public static DistanceCalc2 euclideanEntityReachHorizontal2(Entity entity1, Entity entity2) {
        return (x1, y1, x2, y2) -> {
            float w = entity1.getBbWidth() / 2.0F + entity2.getBbWidth() / 2.0F;

            x1 = Math.max(Math.abs(x1 - x2) - w, 0.0);
            y1 = Math.max(Math.abs(y1 - y2) - w, 0.0);
            return Math.sqrt(x1 * x1 + y1 * y1);
        };
    }

    public static DistanceCalc3 manhattanReach3(double xBS, double yBS, double zBS) {
            return (x1, y1, z1, x2, y2, z2) -> {
            x1 = Math.max(Math.abs(x1 - x2) - xBS, 0.0);
            y1 = Math.max(Math.abs(y1 - y2) - yBS, 0.0);
            z1 = Math.max(Math.abs(z1 - z2) - zBS, 0.0);
            return x1 + y1 + z1;
        };
    }

    public static DistanceCalc3 manhattanEntityReach3(Entity entity1, Entity entity2) {
        return (x1, y1, z1, x2, y2, z2) -> {
            float w = entity1.getBbWidth() / 2.0F + entity2.getBbWidth() / 2.0F;
            float h = entity1.getBbHeight() / 2.0F + entity2.getBbHeight() / 2.0F;

            x1 = Math.max(Math.abs(x1 - x2) - w, 0.0);
            y1 = Math.max(Math.abs(y1 - y2 + h) - h, 0.0);
            z1 = Math.max(Math.abs(z1 - z2) - w, 0.0);
            return x1 + y1 + z1;
        };
    }

    public static DistanceCalc2 manhattanReach2(double xBS, double yBS) {
        return (x1, y1, x2, y2) -> {
            x1 = Math.max(Math.abs(x1 - x2) - xBS, 0.0);
            y1 = Math.max(Math.abs(y1 - y2) - yBS, 0.0);
            return x1 + y1;
        };
    }

    public static DistanceCalc2 manhattanEntityReachHorizontal2(Entity entity1, Entity entity2) {
        return (x1, y1, x2, y2) -> {
            float w = entity1.getBbWidth() / 2.0F + entity2.getBbWidth() / 2.0F;

            x1 = Math.max(Math.abs(x1 - x2) - w, 0.0);
            y1 = Math.max(Math.abs(y1 - y2) - w, 0.0);
            return x1 + y1;
        };
    }
}
