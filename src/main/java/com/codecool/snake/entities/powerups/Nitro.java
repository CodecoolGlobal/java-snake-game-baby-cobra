package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;

public class Nitro extends GameEntity implements Interactable {
    private static Random random = new Random();

    public Nitro() {
        setImage(Globals.getInstance().getImage("Nitro"));

        setX(random.nextDouble() * Globals.WINDOW_WIDTH);
        setY(random.nextDouble() * Globals.WINDOW_HEIGHT);
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
