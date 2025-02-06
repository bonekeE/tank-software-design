package ru.mipt.bit.platformer.logic.actions.entities;

import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.utils.Direction;
import ru.mipt.bit.platformer.logic.objects.interfaces.Moveable;


public class MoveAction implements Action {
    private final Direction direction;
    private final CollisionDetector collisionDetector;
    private final Moveable object;

    public MoveAction(Moveable object, Direction direction, CollisionDetector collisionDetector) {
        this.object = object;
        this.direction = direction;
        this.collisionDetector = collisionDetector;
    }

    public void execute() {
        if (collisionDetector.canMove(object, direction)) {
            object.move(direction);
        }
    }
}
