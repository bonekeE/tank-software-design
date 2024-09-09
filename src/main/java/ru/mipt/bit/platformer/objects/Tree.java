package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.basic.StaticMapObject;

final public class Tree extends StaticMapObject {

    public Tree(String treePath, GridPoint2 coordinates) {
        super(treePath, coordinates);
    }

    @Override
    public void create(Batch batch, TiledMapTileLayer groundLayer) {
        super.create(batch, groundLayer);
    }
}
