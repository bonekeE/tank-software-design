package ru.mipt.bit.platformer.logic.objects.interfaces;

import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.Level;

public interface Shootable extends GameObject{
    boolean isReloaded();
    void shoot(Level level, CollisionDetector collisionDetector);
}
