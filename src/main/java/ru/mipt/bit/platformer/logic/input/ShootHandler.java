package ru.mipt.bit.platformer.logic.input;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.Level;
import ru.mipt.bit.platformer.logic.actions.entities.EmptyAction;
import ru.mipt.bit.platformer.logic.actions.entities.ShootAction;
import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.input.interfaces.InputHandler;

import static com.badlogic.gdx.Input.Keys.*;

public class ShootHandler implements InputHandler {

    private Level level;
    private CollisionDetector collisionDetector;

    public ShootHandler(Level level, CollisionDetector collisionDetector) {
        this.level = level;
        this.collisionDetector = collisionDetector;
    }

    public Action handle() {
        if (Gdx.input.isKeyPressed(SPACE)) {
            return new ShootAction(level.getPlayer(), level, collisionDetector);
        }
        return new EmptyAction();
    }
}
