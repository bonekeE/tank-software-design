package ru.mipt.bit.platformer.logic.objects.interfaces;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Set;

public interface GameObject {
    Set<GridPoint2> getBusyCoordinates();
    void updateState(float deltaTime);
}
