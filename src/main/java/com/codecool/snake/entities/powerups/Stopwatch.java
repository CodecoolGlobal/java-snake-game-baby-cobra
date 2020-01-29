package com.codecool.snake.entities.powerups;

import com.codecool.snake.GameLoop;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.eventhandler.InputHandler;

import java.util.Random;

public class Stopwatch extends GameEntity implements Interactable {
    private static Random random = new Random();

    public Stopwatch() {
        setImage(Globals.getInstance().getImage("Stopwatch"));

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
