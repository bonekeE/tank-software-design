package ru.mipt.bit.platformer.logic.actions.generators;

import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.Level;
import ru.mipt.bit.platformer.logic.actions.entities.BulletMoveAction;
import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;

import java.util.Set;
import java.util.stream.Collectors;

public class BulletsMoveGenerator implements ActionGenerator {

    private final Level level;
    private final CollisionDetector collisionDetector;

    public BulletsMoveGenerator(Level level, CollisionDetector collisionDetector) {
        this.level = level;
        this.collisionDetector = collisionDetector;
    }

    public Set<Action> generate() {
        collisionDetector.checkCollisionsWithBullets();

        return level.getBullets().stream()
                    .map(BulletMoveAction::new)
                    .collect(Collectors.toSet());
    }

}
