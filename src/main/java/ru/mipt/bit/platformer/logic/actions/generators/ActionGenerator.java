package ru.mipt.bit.platformer.logic.actions.generators;

import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;

import java.util.Set;

public interface ActionGenerator {
    Set<Action> generate();
}
