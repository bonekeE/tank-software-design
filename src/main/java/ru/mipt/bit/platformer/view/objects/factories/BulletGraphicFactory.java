package ru.mipt.bit.platformer.view.objects.factories;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.logic.objects.Bullet;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.logic.utils.TileMovement;
import ru.mipt.bit.platformer.view.objects.entities.BulletGraphic;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;
import ru.mipt.bit.platformer.view.objects.interfaces.GraphicObjectFactory;

public class BulletGraphicFactory implements GraphicObjectFactory {
    private final static String IMAGE_PATH = "images/bullet.png";

    private final Batch batch;
    private final TileMovement tileMovement;

    public BulletGraphicFactory(Batch batch, TileMovement tileMovement) {
        this.batch = batch;
        this.tileMovement = tileMovement;
    }

    public GameObjectGraphic create(GameObject gameObject) {
        return new BulletGraphic((Bullet) gameObject, IMAGE_PATH, batch, tileMovement);
    }
}
