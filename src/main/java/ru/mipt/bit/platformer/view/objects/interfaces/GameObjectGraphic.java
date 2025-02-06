package ru.mipt.bit.platformer.view.objects.interfaces;

import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;

public interface GameObjectGraphic {
    void draw();

    void cleanup();

    GameObject getLogicObject();

    Rectangle getRectangle();
}
