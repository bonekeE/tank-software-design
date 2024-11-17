package ru.mipt.bit.platformer.model.util;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {

    UP(90f, new GridPoint2(0,1)),
    DOWN(-90f, new GridPoint2(0, -1)),
    LEFT(-180f, new GridPoint2(-1,0)),
    RIGHT(0f, new GridPoint2(1, 0));

    private final float rotation;
    private final GridPoint2 movementVector;

    Direction(float rotation, GridPoint2 movementVector) {
        this.rotation = rotation;
        this.movementVector = movementVector;
    }

    public float getRotation() {
        return rotation;
    }
}

