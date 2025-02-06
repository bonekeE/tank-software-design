package ru.mipt.bit.platformer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.logic.objects.Bullet;
import ru.mipt.bit.platformer.logic.objects.Tank;
import ru.mipt.bit.platformer.logic.objects.Tree;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.logic.utils.TileMovement;
import ru.mipt.bit.platformer.view.interfaces.LevelListener;
import ru.mipt.bit.platformer.view.objects.entities.HealthBarDecorator;
import ru.mipt.bit.platformer.view.objects.factories.BulletGraphicFactory;
import ru.mipt.bit.platformer.view.objects.factories.FactoryMapper;
import ru.mipt.bit.platformer.view.objects.factories.TankGraphicFactory;
import ru.mipt.bit.platformer.view.objects.factories.TreeGraphicFactory;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.view.utils.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.view.utils.GdxGameUtils.getSingleLayer;

public class LevelDrawer implements LevelListener {
    private final Batch batch;
    private final BitmapFont font;
    private final TiledMap tiledMap;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;

    private final FactoryMapper mapper = new FactoryMapper();
    private final Set<HealthBarDecorator> objects = new HashSet<>();

    private final Map<GameObject, HealthBarDecorator> objectsMapper = new HashMap<>();

    public LevelDrawer(Batch batch, BitmapFont font) {
        this.font = font;
        this.batch = batch;
        this.tiledMap = new TmxMapLoader().load("level.tmx");
        this.levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
        this.groundLayer = getSingleLayer(tiledMap);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        mapper.addFactory(Tree.class, new TreeGraphicFactory(batch, groundLayer));
        mapper.addFactory(Tank.class, new TankGraphicFactory(batch, tileMovement));
        mapper.addFactory(Bullet.class, new BulletGraphicFactory(batch, tileMovement));
    }

    public int getWidth() {
        return groundLayer.getWidth();
    }

    public int getHeight() { return groundLayer.getHeight(); }

    public void addObject(GameObject gameObject) {
        GameObjectGraphic gameObjectGraphic = mapper.create(gameObject);
        HealthBarDecorator healthBarDecorator = new HealthBarDecorator(batch, gameObjectGraphic);

        objects.add(healthBarDecorator);
        objectsMapper.put(gameObject, healthBarDecorator);
    }

    public void removeObject(GameObject gameObject) {
        HealthBarDecorator healthBarDecorator = objectsMapper.get(gameObject);
        objects.remove(healthBarDecorator);
        healthBarDecorator.cleanup();
        objectsMapper.remove(gameObject);
    }


    public void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void draw() {
        levelRenderer.render();
        batch.begin();

        for (GameObjectGraphic object : objects) {
            object.draw();
        }

        int fps = Gdx.graphics.getFramesPerSecond();
        font.draw(batch, "FPS: " + fps, 10, Gdx.graphics.getHeight() - 10);

        batch.end();
    }

    public void switchHealthBar() {
        for (HealthBarDecorator gameObjectGraphic : objects) {
            gameObjectGraphic.switchHealthBar();
        }
    }

    public void cleanup() {
        for (GameObjectGraphic object : objects) {
            object.cleanup();
        }
        batch.dispose();
        font.dispose();
    }

    public void endTheGame() {
        Gdx.app.exit();
    }

}
