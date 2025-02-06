package ru.mipt.bit.platformer.view.objects.interfaces;

import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;

public interface GraphicObjectFactory {
    GameObjectGraphic create(GameObject gameObject);
}
