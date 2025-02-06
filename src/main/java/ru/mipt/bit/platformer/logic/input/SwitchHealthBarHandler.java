package ru.mipt.bit.platformer.logic.input;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.logic.actions.entities.EmptyAction;
import ru.mipt.bit.platformer.logic.actions.entities.SwitchHealthBarAction;
import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.input.interfaces.InputHandler;
import ru.mipt.bit.platformer.view.LevelDrawer;

import static com.badlogic.gdx.Input.Keys.*;

public class SwitchHealthBarHandler implements InputHandler {

    private LevelDrawer levelDrawer;

    public SwitchHealthBarHandler(LevelDrawer levelDrawer) {
        this.levelDrawer = levelDrawer;
    }

    public Action handle() {
        if (Gdx.input.isKeyPressed(L)) {
            return new SwitchHealthBarAction(levelDrawer);
        }
        return new EmptyAction();
    }
}
