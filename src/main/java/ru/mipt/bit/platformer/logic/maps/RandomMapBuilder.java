package ru.mipt.bit.platformer.logic.maps;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic.maps.interfaces.MapBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class RandomMapBuilder implements MapBuilder {
    public final int NUM_TREES = 5;
    public final int NUM_ENEMIES = 3;

    GridPoint2 player;
    Set<GridPoint2> obstacles = new HashSet<GridPoint2>();
    Set<GridPoint2> enemies = new HashSet<GridPoint2>();

    Random random = new Random();

    public RandomMapBuilder(int width, int height) {
        generateTrees(width, height);
        generateEnemies(width, height);
        generatePlayer(width, height);
    }

    private void generateTrees(int width, int height) {
        while (obstacles.size() != NUM_TREES) {
            GridPoint2 randomPoint = getRandomPoint(width, height);
            obstacles.add(randomPoint);
        }
    }

    private void generateEnemies(int width, int height) {
        while (enemies.size() != NUM_ENEMIES) {
            GridPoint2 randomPoint = getRandomPoint(width, height);
            if (!obstacles.contains(randomPoint)) {
                enemies.add(randomPoint);
            }
        }
    }

    private void generatePlayer(int width, int height) {
        do {
            player = getRandomPoint(width, height);
        } while (obstacles.contains(player) || enemies.contains(player));
    }

    private GridPoint2 getRandomPoint(int width, int height) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        return new GridPoint2(x, y);
    }

    public Set<GridPoint2> getObstacles() {
        return obstacles;
    }
    public GridPoint2 getStartPosition() {
        return player;
    }
    public Set<GridPoint2> getStartedEnemies() {
        return enemies;
    }
}
