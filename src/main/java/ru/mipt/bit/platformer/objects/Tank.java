package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.basic.MoveableMapObject;

import static com.badlogic.gdx.math.MathUtils.isEqual;

final public class Tank extends MoveableMapObject {

    public Tank(String treePath, GridPoint2 coordinates) {
        super(treePath, coordinates);
    }

    @Override
    public void create(Batch batch, TiledMapTileLayer groundLayer) {
        super.create(batch, groundLayer);
    }

}

