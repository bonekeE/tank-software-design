package ru.mipt.bit.platformer.logic.utils;

import com.badlogic.gdx.math.GridPoint2;
import org.lwjgl.system.CallbackI;

import java.util.Random;

public enum Direction {

    UP(90f, new GridPoint2(0,1)),
    DOWN(-90f, new GridPoint2(0, -1)),
    LEFT(-180f, new GridPoint2(-1,0)),
    RIGHT(0f, new GridPoint2(1, 0));

    private final float rotation;
    private final GridPoint2 movementVector;

    Direction(float rotation, GridPoint2 movementPoint) {
        this.rotation = rotation;
        this.movementVector = movementPoint;
    }

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 getMovementVector() {
        return movementVector;
    }

    private static final Random rdm = new Random();

    public static Direction getRandomDirection()  {
        Direction[] directions = values();
        return directions[rdm.nextInt(directions.length)];
    }
}

