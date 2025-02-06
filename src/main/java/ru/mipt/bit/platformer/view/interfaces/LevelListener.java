package ru.mipt.bit.platformer.view.interfaces;

import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;

public interface LevelListener {
    void addObject(GameObject gameObject);
    void removeObject(GameObject gameObject);
    void endTheGame();
}
