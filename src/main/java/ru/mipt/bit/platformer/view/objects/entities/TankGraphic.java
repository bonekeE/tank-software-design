package ru.mipt.bit.platformer.view.objects.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.logic.utils.TileMovement;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;
import ru.mipt.bit.platformer.logic.objects.Tank;

import static ru.mipt.bit.platformer.view.utils.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.view.utils.GdxGameUtils.drawTextureRegionUnscaled;

public class TankGraphic implements GameObjectGraphic {
    private final Batch batch;
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final TileMovement tileMovement;
    private final Tank tank;

    public TankGraphic(Tank tank, String imagePath, Batch batch, TileMovement tileMovement) {
        this.tank = tank;
        this.batch = batch;
        this.texture = new Texture(imagePath);
        this.textureRegion = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.textureRegion);
        this.tileMovement = tileMovement;
    }

    public void cleanup() {
        texture.dispose();
    }

    public void draw() {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                tank.getCurrentPosition(),
                tank.getDestinationPosition(),
                tank.getMovementProgress()
        );
        drawTextureRegionUnscaled(
                batch,
                textureRegion,
                rectangle,
                tank.getDirection().getRotation()
        );
    }

    public GameObject getLogicObject() {
        return tank;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
