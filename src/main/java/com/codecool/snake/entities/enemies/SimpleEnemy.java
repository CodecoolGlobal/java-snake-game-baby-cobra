package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;


public class SimpleEnemy extends Enemy implements Animatable, Interactable {
    private double direction = rnd.nextDouble() * 360;
    private Point2D heading;
    private static Random rnd = new Random();
    private int speed = 1;

    public SimpleEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }


    @Override
    public void step() {
        if (isOutOfBounds()) {
            double randomMultiplier = Utils.glanceOfRandomizer();
            direction = direction - 180+randomMultiplier;
            heading = Utils.directionToVector(direction, speed);
            setRotate(direction);

        }
        heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());


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
