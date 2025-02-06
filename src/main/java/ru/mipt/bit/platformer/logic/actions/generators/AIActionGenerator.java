package ru.mipt.bit.platformer.logic.actions.generators;

import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.Level;
import ru.mipt.bit.platformer.logic.actions.entities.MoveAction;
import ru.mipt.bit.platformer.logic.actions.entities.ShootAction;
import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.objects.Tank;
import ru.mipt.bit.platformer.logic.utils.Direction;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class AIActionGenerator implements ActionGenerator{

    private Level level;
    private CollisionDetector collisionDetector;

    private Random random = new Random();
    private final int SHOOT_FREQUENCY = 1;

    public AIActionGenerator(Level level, CollisionDetector collisionDetector) {
        this.level = level;
        this.collisionDetector = collisionDetector;
    }

    public Set<Action> generate() {
        Set<Action> actions = new HashSet<>();

        for (Tank tank : level.getEnemies()) {
            Direction direction = Direction.getRandomDirection();
            actions.add(new MoveAction(tank, direction, collisionDetector));

            boolean isShootReady = getRandomInt(0, SHOOT_FREQUENCY) % SHOOT_FREQUENCY == 0;
            if (isShootReady) {
                actions.add(new ShootAction(tank, level, collisionDetector));
            }
        }
        return actions;
    }

    private int getRandomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
