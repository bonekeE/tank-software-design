package ru.mipt.bit.platformer.logic;


import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic.objects.Bullet;
import ru.mipt.bit.platformer.logic.objects.Tank;
import ru.mipt.bit.platformer.logic.objects.Tree;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.logic.objects.interfaces.Moveable;
import ru.mipt.bit.platformer.logic.utils.Direction;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class CollisionDetector {

    private final Level level;
    private final int height;
    private final int width;

    public CollisionDetector(Level level, int height, int width) {
        this.level = level;
        this.height = height;
        this.width = width;
    }

    public boolean isInsideMap(GridPoint2 point) {
        return (point.y < height && point.y >= 0) && (point.x < width && point.x >= 0);
    }

    public boolean canMove(Moveable object, Direction direction) {
        Set<GridPoint2> obstacles = getObstacles(object);

        GridPoint2 destinationPosition = object.getCurrentPosition().cpy().add(direction.getMovementVector());
        return isInsideMap(destinationPosition) && !obstacles.contains(destinationPosition);
    }

    private Set<GridPoint2> getObstacles(Moveable object) {
        Set<GameObject> gameObjects = level.getObjects();

        Set<GridPoint2> obstacles = new HashSet<>();
        for (GameObject gameObject : gameObjects) {
            obstacles.addAll(gameObject.getBusyCoordinates());
        }
        obstacles.removeAll(object.getBusyCoordinates());

        return obstacles;
    }

    private boolean detectCollision(GameObject obj1, GameObject obj2) {
        Set<GridPoint2> obj1Coordinates = obj1.getBusyCoordinates();
        Set<GridPoint2> obj2Coordinates = obj2.getBusyCoordinates();

        for (GridPoint2 coord : obj1Coordinates) {
            if (obj2Coordinates.contains(coord)) {
                return true;
            }
        }
        return false;
    }

    public void checkCollisionsWithBullets() {
        Set<Bullet> bullets = level.getBullets();
        Set<Tree> trees = level.getTrees();
        Set<Tank> tanks = level.getTanks();

        Set<Bullet> bulletsToRemove = new HashSet<>();

        checkCollisions(trees, bullets, bulletsToRemove, (tree, bullet) -> {});

        checkCollisions(tanks, bullets, bulletsToRemove, (tank, bullet) -> tank.takeDamage(bullet.getBulletDamage()));

        Set<Tank> tanksToRemove = tanks.stream().filter(tank -> tank.getHealthPoints() <= 0).collect(Collectors.toSet());

        bulletsToRemove.forEach(level::removeBullet);
        tanksToRemove.forEach(level::removeTank);
    }

    private <T extends GameObject> void checkCollisions(Set<T> objects,
                                                        Set<Bullet> bullets,
                                                        Set<Bullet> bulletsToRemove,
                                                        BiConsumer<T, Bullet> onCollision) {
        for (T object : objects) {
            for (Bullet bullet : bullets) {
                if (detectCollision(object, bullet)) {
                    onCollision.accept(object, bullet);
                    bulletsToRemove.add(bullet);
                }
            }
        }
    }
}
