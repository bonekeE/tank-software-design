package ru.mipt.bit.platformer.view.objects.factories;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.logic.objects.Tree;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.view.objects.entities.TreeGraphic;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;
import ru.mipt.bit.platformer.view.objects.interfaces.GraphicObjectFactory;

public class TreeGraphicFactory implements GraphicObjectFactory {
    private final static String IMAGE_PATH = "images/greenTree.png";

    private final Batch batch;
    private final TiledMapTileLayer groundLayer;

    public TreeGraphicFactory(Batch batch, TiledMapTileLayer groundLayer) {
        this.batch = batch;
        this.groundLayer = groundLayer;
    }

    public GameObjectGraphic create(GameObject gameObject) {
        return new TreeGraphic((Tree) gameObject, IMAGE_PATH, batch, groundLayer);
    }
}
