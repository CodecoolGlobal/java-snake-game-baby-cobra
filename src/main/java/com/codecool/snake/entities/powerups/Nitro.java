package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;

public class Nitro extends PowerUp {

    public Nitro() {
        super("Nitro");
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return null;
    }
}
