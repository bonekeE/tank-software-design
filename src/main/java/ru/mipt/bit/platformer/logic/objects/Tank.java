package ru.mipt.bit.platformer.logic.objects;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.TimeUtils;
import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.Level;
import ru.mipt.bit.platformer.logic.objects.interfaces.Liveable;
import ru.mipt.bit.platformer.logic.objects.interfaces.Moveable;
import ru.mipt.bit.platformer.logic.objects.interfaces.Shootable;
import ru.mipt.bit.platformer.logic.utils.Direction;
import ru.mipt.bit.platformer.view.utils.GdxGameUtils;

import java.util.HashSet;
import java.util.Set;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Tank implements Moveable, Liveable, Shootable {
    private GridPoint2 currentPosition;
    private GridPoint2 destinationPosition;
    private Direction direction;
    private float movementProgress = 1f;

    private static final long RELOAD_TIME_MS = 1000;
    private static final float MOVEMENT_SPEED = 0.4f;

    private int healthPoints = 100;
    private long lastShootTime;

    public Tank(GridPoint2 position) {
        this.direction = Direction.RIGHT;
        this.currentPosition = position;
        this.destinationPosition = position;
        this.lastShootTime = TimeUtils.millis();
    }

    public Set<GridPoint2> getBusyCoordinates() {
        Set<GridPoint2> busyCoordinates = new HashSet<>();

        busyCoordinates.add(currentPosition);
        busyCoordinates.add(destinationPosition);

        return busyCoordinates;
    }

    public void updateState(float deltaTime) {
        movementProgress = GdxGameUtils.continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isMovingProceed()) {
            currentPosition.set(destinationPosition);
        }
    }

    public void move(Direction direction) {
        if (isMovingProceed()) {
            movementProgress = 0f;
            destinationPosition = currentPosition.cpy().add(direction.getMovementVector());
            this.direction = direction;
        }
    }

    public boolean isMovingProceed() {
        return isEqual(movementProgress, 1f);
    }

    public GridPoint2 getCurrentPosition() {
        return currentPosition;
    }

    public GridPoint2 getDestinationPosition() {
        return destinationPosition;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public Direction getDirection() { return direction; }

    public int getHealthPoints() { return healthPoints; }

    public void takeDamage(int damage) { healthPoints -= damage; }

    public boolean isReloaded() {
        if (TimeUtils.timeSinceMillis(lastShootTime) > RELOAD_TIME_MS) {
            lastShootTime = TimeUtils.millis();
            return true;
        }
        return false;
    }

    public void shoot(Level level, CollisionDetector collisionDetector) {
        GridPoint2 bulletPosition = destinationPosition.cpy().add(direction.getMovementVector());
        if (collisionDetector.isInsideMap(bulletPosition)) {
            Bullet bullet = new Bullet(bulletPosition, direction);
            level.addBullet(bullet);
        }
    }
}
