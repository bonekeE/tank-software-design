package ru.mipt.bit.platformer.view.objects.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.logic.objects.interfaces.Liveable;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;
import ru.mipt.bit.platformer.view.utils.GdxGameUtils;


public class HealthBarDecorator implements GameObjectGraphic {

    private Batch batch;
    private GameObjectGraphic gameObjectGraphic;
    private boolean drawHealthBar = true;

    public HealthBarDecorator(Batch batch, GameObjectGraphic gameObjectGraphic) {
        this.batch = batch;
        this.gameObjectGraphic = gameObjectGraphic;
    }

    public void draw() {
        if (drawHealthBar) {
            drawHealthBar();
        }
        gameObjectGraphic.draw();
    }

    public void switchHealthBar() {
        drawHealthBar = !drawHealthBar;
    }

    private void drawHealthBar() {
        GameObject gameObject = gameObjectGraphic.getLogicObject();
        if (gameObject instanceof Liveable) {
            int healthPoints = (int) ((Liveable) gameObject).getHealthPoints();

            TextureRegion healthBarTextureRegion = getHealthBarTexture(healthPoints);
            Rectangle rectangle = createRectangle(healthBarTextureRegion);

            GdxGameUtils.drawTextureRegionUnscaled(batch, healthBarTextureRegion, rectangle, 0f);
        }
    }

    private Rectangle createRectangle(TextureRegion healthBarTextureRegion) {
        Rectangle rectangle_ = new Rectangle(gameObjectGraphic.getRectangle());
        rectangle_.y += 90;
        return rectangle_;
    }

    private TextureRegion getHealthBarTexture(float health) {
        Pixmap pixmap = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, 0, 100, 20);
        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(0, 0, (int) (health), 20);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegion(texture);
    }

    public void cleanup() {
        gameObjectGraphic.cleanup();
    }

    public GameObject getLogicObject() {
        return gameObjectGraphic.getLogicObject();
    }

    public Rectangle getRectangle() {
        return gameObjectGraphic.getRectangle();
    }
}
