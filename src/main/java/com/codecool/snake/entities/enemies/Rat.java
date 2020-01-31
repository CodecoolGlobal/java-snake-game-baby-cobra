package com.codecool.snake.entities.enemies;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

public class Rat extends Enemy implements Animatable, Interactable {
    private Point2D heading;
    private static Random rnd = new Random();
    private double direction = rnd.nextDouble() * 360;
    private double speed = 1.5;

    public Rat() {
        super("Rat", -10);
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            double randomMultiplier = Utils.glanceOfRandomizer();
            direction = direction - 180 + randomMultiplier;
            heading = Utils.directionToVector(direction, speed);
            setRotate(direction);

        }
        heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        setRotate(direction);
    }

    @Override
    public void apply(GameEntity entity) {

        if (entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
