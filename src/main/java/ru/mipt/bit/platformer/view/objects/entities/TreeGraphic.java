package ru.mipt.bit.platformer.view.objects.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.logic.objects.Tree;
import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;

import static ru.mipt.bit.platformer.view.utils.GdxGameUtils.*;

public class TreeGraphic implements GameObjectGraphic {
    private final Batch batch;
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Tree tree;

    public TreeGraphic(Tree tree, String imagePath, Batch batch, TiledMapTileLayer groundLayer) {
        this.tree = tree;
        this.batch = batch;
        this.texture = new Texture(imagePath);
        this.textureRegion = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.textureRegion);

        moveRectangleAtTileCenter(groundLayer, rectangle, tree.getCoordinates());
    }

    public void cleanup() {
        texture.dispose();
    }

    public GameObject getLogicObject() {
        return tree;
    }

    public void draw() {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, 0f);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
