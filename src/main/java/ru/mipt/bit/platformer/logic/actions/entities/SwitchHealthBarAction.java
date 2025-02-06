package ru.mipt.bit.platformer.logic.actions.entities;

import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.view.LevelDrawer;

public class SwitchHealthBarAction implements Action {

    private final LevelDrawer levelDrawer;

    public SwitchHealthBarAction(LevelDrawer levelDrawer) {
        this.levelDrawer = levelDrawer;
    }

    public void execute() {
        levelDrawer.switchHealthBar();
    }
}
