package ru.mipt.bit.platformer.logic.actions.entities;

import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.Level;
import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.objects.interfaces.Shootable;

public class ShootAction implements Action {

    private final Level level;
    private final Shootable gameObject;
    private final CollisionDetector collisionDetector;

    public ShootAction(Shootable gameObject, Level level, CollisionDetector collisionDetector) {
        this.level = level;
        this.gameObject = gameObject;
        this.collisionDetector = collisionDetector;
    }

    public void execute() {
        if (gameObject.isReloaded()) {
            gameObject.shoot(level, collisionDetector);
        }
    }
}
