package ru.mipt.bit.platformer.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.model.util.Direction;

import static ru.mipt.bit.platformer.util.GdxGameUtils.incrementedY;

public class Tank {
    private GridPoint2 currentPosition;
    private GridPoint2 destinationPosition;
    private Direction direction;

    private float movementProgress = 1f;

    public Tank(GridPoint2 position) {
        this.currentPosition = position;
        this.destinationPosition = position;
        this.direction = Direction.RIGHT;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    public GridPoint2 getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(GridPoint2 position) {
        this.currentPosition = position;
    }

    public GridPoint2 getDestinationPosition() {
        return destinationPosition;
    }

    public void setDestinationPosition(GridPoint2 position) {
        this.destinationPosition = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
