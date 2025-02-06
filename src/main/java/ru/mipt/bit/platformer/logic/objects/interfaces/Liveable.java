package ru.mipt.bit.platformer.logic.objects.interfaces;

public interface Liveable extends GameObject {
    int getHealthPoints();
    void takeDamage(int damage);
}
