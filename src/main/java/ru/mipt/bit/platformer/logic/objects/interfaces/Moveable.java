package ru.mipt.bit.platformer.logic.objects.interfaces;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic.utils.Direction;

public interface Moveable extends GameObject {
    void move(Direction direction);
    GridPoint2 getCurrentPosition();
    boolean isMovingProceed();
}
