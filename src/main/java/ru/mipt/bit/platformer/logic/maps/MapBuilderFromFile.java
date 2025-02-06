package ru.mipt.bit.platformer.logic.maps;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic.maps.interfaces.MapBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapBuilderFromFile implements MapBuilder {

    private GridPoint2 player;
    private Set<GridPoint2> obstacles = new HashSet<>();
    private Set<GridPoint2> enemies = new HashSet<>();

    public MapBuilderFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        int y = lines.size() - 1;
        for (String s: lines) {
            int x = 0;
            for (char c: s.toCharArray()) {
                if (c == 'T') {
                    obstacles.add(new GridPoint2(x, y));
                } else if (c == 'X') {
                    player = new GridPoint2(x, y);
                } else if (c == 'E') {
                    enemies.add(new GridPoint2(x ,y));
                }
                x++;
            }
            y--;
        }
    }

    public Set<GridPoint2> getObstacles() {
        return obstacles;
    }

    public Set<GridPoint2> getStartedEnemies() {
        return enemies;
    }

    public GridPoint2 getStartPosition() {
        return player;
    }
}
