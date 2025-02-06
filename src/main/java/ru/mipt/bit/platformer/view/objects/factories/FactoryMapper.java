package ru.mipt.bit.platformer.view.objects.factories;

import ru.mipt.bit.platformer.logic.objects.interfaces.GameObject;
import ru.mipt.bit.platformer.view.objects.interfaces.GameObjectGraphic;
import ru.mipt.bit.platformer.view.objects.interfaces.GraphicObjectFactory;

import java.util.HashMap;
import java.util.Map;

public class FactoryMapper {
    private final Map<Class<? extends GameObject>, GraphicObjectFactory> mapper = new HashMap<>();

    public void addFactory(Class<? extends GameObject> gameObjectClass, GraphicObjectFactory factory) {
        mapper.put(gameObjectClass, factory);
    }

    public GameObjectGraphic create(GameObject gameObject) throws IllegalArgumentException {
        Class<? extends GameObject> gameObjectClass = gameObject.getClass();

        GraphicObjectFactory factory = mapper.get(gameObjectClass);
        if (factory != null) {
            return factory.create(gameObject);
        }

        throw new IllegalArgumentException("No factory found for class: " + gameObjectClass.getName());
    }
}
