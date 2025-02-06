package ru.mipt.bit.platformer.logic.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;

import java.util.HashSet;
import java.util.Set;

public class Tree implements GameObject {
    private final GridPoint2 coordinates;

    public Tree(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Set<GridPoint2> getBusyCoordinates() {
        Set<GridPoint2> busyCoordinates = new HashSet<GridPoint2>();
        busyCoordinates.add(coordinates);

        return busyCoordinates;
    }

    public void updateState(float deltaTime) {

    }
}
