package ru.mipt.bit.platformer.logic.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.logic.utils.Direction;
import ru.mipt.bit.platformer.view.utils.GdxGameUtils;

import java.util.HashSet;
import java.util.Set;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Bullet implements GameObject {
    private Direction direction;
    private GridPoint2 currentPosition;
    private GridPoint2 destinationPosition;
    private float movementProgress = 1f;

    private static final int BULLET_DAMAGE = 10;
    private static final float MOVEMENT_SPEED = 0.4f;

    public Bullet(GridPoint2 position, Direction direction) {
        this.direction = direction;
        this.currentPosition = position.cpy();
        this.destinationPosition = position.cpy();
    }

    public void move() {
        if (isMovingProceed()) {
            movementProgress = 0f;
            destinationPosition = currentPosition.cpy().add(direction.getMovementVector());
        }
    }

    public int getBulletDamage() { return BULLET_DAMAGE; }

    public Direction getDirection() { return direction; }

    public GridPoint2 getCurrentPosition() { return currentPosition; }

    public GridPoint2 getDestinationPosition() { return destinationPosition; }

    public float getMovementProgress() { return movementProgress; }

    public boolean isMovingProceed() {return isEqual(movementProgress, 1f);}

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
}
