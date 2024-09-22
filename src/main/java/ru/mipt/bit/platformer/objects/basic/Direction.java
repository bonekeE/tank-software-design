package ru.mipt.bit.platformer.objects.basic;

import lombok.Getter;

@Getter
public enum Direction {
    NoWAY(0f),
    NORTH(90f),
    WEST(-180f),
    SOUTH(-90f),
    EAST(0f);

    private final Float value;

    Direction(Float value) {
        this.value = value;
    }
}
