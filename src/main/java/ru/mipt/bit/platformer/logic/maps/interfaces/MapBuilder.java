package ru.mipt.bit.platformer.logic.maps.interfaces;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Set;

public interface MapBuilder {
    Set<GridPoint2> getObstacles();
    Set<GridPoint2> getStartedEnemies();
    GridPoint2 getStartPosition();
}
