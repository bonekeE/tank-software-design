package ru.mipt.bit.platformer.view.objects.factories;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.logic.objects.Tank;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.view.objects.entities.TankGraphic;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;
import ru.mipt.bit.platformer.view.objects.interfaces.GraphicObjectFactory;
import ru.mipt.bit.platformer.logic.utils.TileMovement;

public class TankGraphicFactory implements GraphicObjectFactory {
    private final static String IMAGE_PATH = "images/tank_blue.png";

    private final Batch batch;
    private final TileMovement tileMovement;

    public TankGraphicFactory(Batch batch, TileMovement tileMovement) {
        this.batch = batch;
        this.tileMovement = tileMovement;
    }

    public GameObjectGraphic create(GameObject gameObject) {
        return new TankGraphic((Tank) gameObject, IMAGE_PATH, batch, tileMovement);
    }
}
