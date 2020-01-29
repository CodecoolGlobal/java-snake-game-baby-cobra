package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;

public abstract class Enemy extends GameEntity{
    private final int damage;

    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public static void destroyAllEnemies() {
        for (GameEntity gameEntity: Globals.getInstance().display.getObjectList()) {
            if (gameEntity instanceof Enemy) {
                gameEntity.destroy();
            }
        }
    }
}
