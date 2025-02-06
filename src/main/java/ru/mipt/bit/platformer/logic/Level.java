package ru.mipt.bit.platformer.logic;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic.maps.interfaces.MapBuilder;
import ru.mipt.bit.platformer.logic.objects.Bullet;
import ru.mipt.bit.platformer.logic.objects.Tank;
import ru.mipt.bit.platformer.logic.objects.Tree;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.view.interfaces.LevelListener;

import java.util.HashSet;
import java.util.Set;

public class Level {
    private Integer height;
    private Integer width;

    private Set<LevelListener> subscribers = new HashSet<>();

    private Tank player;
    private Set<Tree> trees = new HashSet<>();
    private Set<Tank> enemies = new HashSet<>();
    private Set<Bullet> bullets = new HashSet<>();

    public Level() {}

    public void initialize(MapBuilder mapBuilder) {
        for (GridPoint2 coord : mapBuilder.getObstacles()) {
            addTree(new Tree(coord));
        }

        for (GridPoint2 coord : mapBuilder.getStartedEnemies()) {
            addEnemy(new Tank(coord));
        }

        addPlayer(new Tank(mapBuilder.getStartPosition()));
    }

    public void addSubscriber(LevelListener subscriber) {
        subscribers.add(subscriber);
    }

    public Set<GameObject> getObjects() {
        Set<GameObject> objects = new HashSet<>();
        objects.add(player);
        objects.addAll(trees);
        objects.addAll(enemies);
        objects.addAll(bullets);

        return objects;
    }

    public void update(float deltaTime) {
        Set<GameObject> gameObjects = getObjects();
        gameObjects.forEach(gameObject -> gameObject.updateState(deltaTime));
    }

    public Tank getPlayer() {
        return player;
    }

    public Set<Tank> getEnemies() { return enemies; }

    public Set<Bullet> getBullets() {
        return bullets;
    }

    public Set<Tree> getTrees() { return trees; };

    public Set<Tank> getTanks() {
        Set<Tank> tanks = new HashSet<>(enemies);
        tanks.add(player);

        return tanks;
    }

    private void addPlayer(Tank tank) {
        player = tank;
        subscribers.forEach(s -> s.addObject(tank));
    }

    private void addTree(Tree tree) {
        trees.add(tree);
        subscribers.forEach(s -> s.addObject(tree));
    }

    private void addEnemy(Tank tank) {
        enemies.add(tank);
        subscribers.forEach(s -> s.addObject(tank));
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
        subscribers.forEach(s -> s.addObject(bullet));
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
        subscribers.forEach(s -> s.removeObject(bullet));
    }

    public void removeTank(Tank tank) {
        if (tank.equals(player)) {
            subscribers.forEach(LevelListener::endTheGame);
        }
        enemies.remove(tank);
        subscribers.forEach(s -> s.removeObject(tank));
    }
}
