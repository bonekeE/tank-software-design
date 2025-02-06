package ru.mipt.bit.platformer.logic.actions.entities;

import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.objects.Bullet;

public class BulletMoveAction implements Action {

    private final Bullet bullet;

    public BulletMoveAction(Bullet bullet) {
        this.bullet = bullet;
    }

    public void execute() {
        bullet.move();
    }
}
