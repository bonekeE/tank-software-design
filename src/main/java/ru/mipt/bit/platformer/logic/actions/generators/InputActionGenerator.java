package ru.mipt.bit.platformer.logic.actions.generators;

import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.input.interfaces.InputHandler;

import java.util.HashSet;
import java.util.Set;

public class InputActionGenerator implements ActionGenerator{

    private Set<InputHandler> inputHandlers = new HashSet<>();

    public InputActionGenerator() {
    }

    public InputActionGenerator addInputHandler(InputHandler handler) {
        inputHandlers.add(handler);
        return this;
    }

    public Set<Action> generate() {
        Set<Action> actions = new HashSet<>();
        for (InputHandler inputHandler : inputHandlers) {
            actions.add(inputHandler.handle());
        }

        return actions;
    }
}
