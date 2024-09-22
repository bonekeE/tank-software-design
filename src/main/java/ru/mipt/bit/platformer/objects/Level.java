package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

import lombok.Getter;


final public class Level {
    private String levelPath;
    private TiledMap level;
    @Getter private MapRenderer levelRenderer;
    @Getter private TileMovement tileMovement;
    @Getter private TiledMapTileLayer groundLayer;

    private Batch batch;

    public Level(String levelPath) {
        this.levelPath = levelPath;
    }

    public void create(Batch batch) {
        this.batch = batch;

        // load level tiles
        level = new TmxMapLoader().load(levelPath);
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public void dispose() {
        level.dispose();
    }
}
