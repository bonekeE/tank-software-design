package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.basic.Direction;
import ru.mipt.bit.platformer.objects.Level;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

final public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;

    private Level level = new Level("level.tmx");
    private Tank tankPlayer = new Tank("images/tank_blue.png", new GridPoint2(1, 1));
    private Tree treeObstacle = new Tree("images/greenTree.png", new GridPoint2(1, 3));

    @Override
    public void create() {
        batch = new SpriteBatch();
        level.create(batch);
        tankPlayer.create(batch, level.getGroundLayer());
        treeObstacle.create(batch, level.getGroundLayer());
    }

    @Override
    public void render() {
        clearScreen();
        handlePlayerMovement();
        moveObjects();
        drawObjects();
    }

    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    private void moveObjects() {
        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();
        tankPlayer.move(level.getTileMovement(), deltaTime, MOVEMENT_SPEED);
    }

    private void handlePlayerMovement() {
        Direction direction = determineDirection();

        if (direction != Direction.NoWAY && isEqual(tankPlayer.movementProgress, 1f)) {
            switch (direction) {
                case NORTH:
                    if (tankPlayer.canMove(decrementedY(treeObstacle.coordinates))) {
                        tankPlayer.destinationCoordinates.y++;
                        tankPlayer.movementProgress = 0f;
                    }
                    break;
                case SOUTH:
                    if (tankPlayer.canMove(incrementedY(treeObstacle.coordinates))) {
                        tankPlayer.destinationCoordinates.y--;
                        tankPlayer.movementProgress = 0f;
                    }
                    break;
                case WEST:
                    if (tankPlayer.canMove(incrementedX(treeObstacle.coordinates))) {
                        tankPlayer.destinationCoordinates.x--;
                        tankPlayer.movementProgress = 0f;
                    }
                    break;
                case EAST:
                    if (tankPlayer.canMove(decrementedX(treeObstacle.coordinates))) {
                        tankPlayer.destinationCoordinates.x++;
                        tankPlayer.movementProgress = 0f;
                    }
                    break;
            }
            tankPlayer.rotation = direction.getValue();
        }
    }

    private Direction determineDirection() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return Direction.NORTH;
        } else if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return Direction.WEST;
        } else if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return Direction.EAST;
        } else if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return Direction.SOUTH;
        }
        return Direction.NoWAY;
    }

    private void drawObjects() {
        // render each tile of the level
        level.getLevelRenderer().render();

        // start recording all drawing commands
        batch.begin();

        // render player
        tankPlayer.draw();

        // render tree obstacle
        treeObstacle.draw();

        // submit all drawing requests
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        treeObstacle.dispose();
        tankPlayer.dispose();
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
