package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mipt.bit.platformer.logic.Level;
import ru.mipt.bit.platformer.logic.maps.RandomMapBuilder;
import ru.mipt.bit.platformer.logic.maps.interfaces.MapBuilder;
import ru.mipt.bit.platformer.view.LevelDrawer;

import static ru.mipt.bit.platformer.GameDesktopLauncher.WIDTH;
import static ru.mipt.bit.platformer.GameDesktopLauncher.HEIGHT;

@Configuration
public class Config {

    @Bean
    public MapBuilder mapInitObjects() {
        return new RandomMapBuilder(WIDTH, HEIGHT);
    }

    @Bean
    public Level logicLevel() {
        return new Level();
    }

    @Bean
    public LevelDrawer levelDrawer() {
        return new LevelDrawer(new SpriteBatch(), new BitmapFont());
    }
}
