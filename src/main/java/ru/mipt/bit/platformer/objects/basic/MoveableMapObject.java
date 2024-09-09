package ru.mipt.bit.platformer.objects.basic;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class MoveableMapObject extends StaticMapObject {
    public GridPoint2 destinationCoordinates;
    public float movementProgress = 1f;

    public MoveableMapObject(String treePath, GridPoint2 coordinates) {
        super(treePath, coordinates);
        destinationCoordinates = new GridPoint2(coordinates);
    }

    public boolean canMove(GridPoint2 obstacleCoordinates) {
        return !coordinates.equals(obstacleCoordinates);
    }

    public void move(TileMovement tileMovement, float deltaTime, float MOVEMENT_SPEED) {
        // calculate interpolated player screen coordinates
        tileMovement.moveRectangleBetweenTileCenters(rectangle, coordinates, destinationCoordinates, movementProgress);

        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(movementProgress, 1f)) {
            // record that the player has reached his/her destination
            coordinates.set(destinationCoordinates);
        }
    }
}
