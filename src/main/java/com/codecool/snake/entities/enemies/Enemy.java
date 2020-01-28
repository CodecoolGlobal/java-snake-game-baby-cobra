package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;

public abstract class Enemy extends GameEntity{
    private final int damage;

    public Enemy(int damage) {
        super();
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
