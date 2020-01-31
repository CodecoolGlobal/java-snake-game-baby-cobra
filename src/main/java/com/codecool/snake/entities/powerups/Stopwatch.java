package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;

public class Stopwatch extends PowerUp {

    public Stopwatch() {
        super("Stopwatch");
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
