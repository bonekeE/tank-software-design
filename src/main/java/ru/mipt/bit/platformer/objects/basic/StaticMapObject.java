package ru.mipt.bit.platformer.objects.basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class StaticMapObject {
    protected String texturePath;
    protected Texture texture;
    protected TextureRegion graphics;
    @Getter Rectangle rectangle;

    protected Batch batch;

    public GridPoint2 coordinates;
    public float rotation = 0f;

    public StaticMapObject(String texturePath, GridPoint2 coordinates) {
        this.texturePath = texturePath;
        this.coordinates = coordinates;
    }

    public void create(Batch batch, TiledMapTileLayer groundLayer) {
        this.batch = batch;
        createTexture();

        moveRectangleAtTileCenter(groundLayer, rectangle, coordinates);
    }

    public void draw() {
        drawTextureRegionUnscaled(batch, graphics, rectangle, rotation);
    }

    protected void createTexture() {
        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        texture = new Texture(texturePath);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
    }

    public void dispose() {
        texture.dispose();
    }
}

