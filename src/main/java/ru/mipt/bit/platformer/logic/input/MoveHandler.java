package ru.mipt.bit.platformer.logic.input;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.actions.entities.EmptyAction;
import ru.mipt.bit.platformer.logic.actions.entities.MoveAction;
import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.input.interfaces.InputHandler;
import ru.mipt.bit.platformer.logic.objects.interfaces.Moveable;
import ru.mipt.bit.platformer.logic.utils.Direction;

import static com.badlogic.gdx.Input.Keys.*;

public class MoveHandler implements InputHandler {

    private Moveable object;
    private CollisionDetector collisionDetector;

    public MoveHandler(Moveable object, CollisionDetector collisionDetector) {
        this.object = object;
        this.collisionDetector = collisionDetector;
    }

    public Action handle() {

        Direction nextDirection = Direction.RIGHT;
        boolean isPressed = false;
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            isPressed = true;
            nextDirection = Direction.UP;
        }

        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            isPressed = true;
            nextDirection = Direction.LEFT;
        }

        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            isPressed = true;
            nextDirection = Direction.DOWN;
        }

        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            isPressed = true;
            nextDirection = Direction.RIGHT;
        }

        if (isPressed) {
            return new MoveAction(object, nextDirection, collisionDetector);
        }

        return new EmptyAction();
    }
}
