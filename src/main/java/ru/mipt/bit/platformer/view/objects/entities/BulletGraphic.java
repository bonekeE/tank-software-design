package ru.mipt.bit.platformer.view.objects.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logic.objects.Bullet;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.logic.utils.TileMovement;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;

import static ru.mipt.bit.platformer.view.utils.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.view.utils.GdxGameUtils.drawTextureRegionUnscaled;

public class BulletGraphic implements GameObjectGraphic {
    private final Batch batch;
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final TileMovement tileMovement;
    private final Bullet bullet;

    public BulletGraphic(Bullet bullet, String imagePath, Batch batch, TileMovement tileMovement) {
        this.bullet = bullet;
        this.batch = batch;
        this.texture = new Texture(imagePath);
        this.textureRegion = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.textureRegion);
        this.tileMovement = tileMovement;
    }

    public void draw() {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                bullet.getCurrentPosition(),
                bullet.getDestinationPosition(),
                bullet.getMovementProgress()
        );
        drawTextureRegionUnscaled(
                batch,
                textureRegion,
                rectangle,
                bullet.getDirection().getRotation()
        );
    }

    public void cleanup() {texture.dispose();}

    public GameObject getLogicObject() {
        return bullet;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
