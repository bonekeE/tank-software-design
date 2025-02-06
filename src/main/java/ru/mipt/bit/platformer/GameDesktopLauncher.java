package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mipt.bit.platformer.logic.CollisionDetector;
import ru.mipt.bit.platformer.logic.Level;
import ru.mipt.bit.platformer.logic.actions.entities.interfaces.Action;
import ru.mipt.bit.platformer.logic.actions.generators.AIActionGenerator;
import ru.mipt.bit.platformer.logic.actions.generators.ActionGenerator;
import ru.mipt.bit.platformer.logic.actions.generators.BulletsMoveGenerator;
import ru.mipt.bit.platformer.logic.actions.generators.InputActionGenerator;
import ru.mipt.bit.platformer.logic.input.MoveHandler;
import ru.mipt.bit.platformer.logic.input.ShootHandler;
import ru.mipt.bit.platformer.logic.input.SwitchHealthBarHandler;
import ru.mipt.bit.platformer.logic.maps.interfaces.MapBuilder;
import ru.mipt.bit.platformer.view.LevelDrawer;

import java.util.*;

public class GameDesktopLauncher implements ApplicationListener {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 8;

    private Level level;
    private LevelDrawer drawer;
    private CollisionDetector collisionDetector;

    private Queue<Action> actionsQueue = new ArrayDeque<>();
    private Set<ActionGenerator> actionGenerators = new HashSet<>();

    @Override
    public void create() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        level = context.getBean(Level.class);
        drawer = context.getBean(LevelDrawer.class);

        level.addSubscriber(drawer);
        level.initialize(context.getBean(MapBuilder.class));

        collisionDetector = new CollisionDetector(level, drawer.getHeight(), drawer.getWidth());

        InputActionGenerator inputActionGenerator = new InputActionGenerator().
                                                        addInputHandler(new MoveHandler(level.getPlayer(), collisionDetector)).
                                                        addInputHandler(new ShootHandler(level, collisionDetector)).
                                                        addInputHandler(new SwitchHealthBarHandler(drawer));

        AIActionGenerator aiActionGenerator = new AIActionGenerator(level, collisionDetector);
        BulletsMoveGenerator bulletsMoveGenerator = new BulletsMoveGenerator(level, collisionDetector);

        actionGenerators.add(inputActionGenerator);
        actionGenerators.add(aiActionGenerator);
        actionGenerators.add(bulletsMoveGenerator);
    }

    @Override
    public void render() {
        drawer.clearScreen();

        for (ActionGenerator generator : actionGenerators) {
            Set<Action> actions = generator.generate();
            actionsQueue.addAll(actions);
        }

        while (!actionsQueue.isEmpty()) {
            Action action = actionsQueue.poll();
            action.execute();
        }

        level.update(Gdx.graphics.getDeltaTime());
        drawer.draw();
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
        drawer.cleanup();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
