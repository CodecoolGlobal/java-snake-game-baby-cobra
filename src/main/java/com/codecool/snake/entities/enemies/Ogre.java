package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

public class Ogre extends Enemy implements Animatable, Interactable {
    private Point2D heading;
    private int speed = 3;
    private double dir = 90;
    private int stepCycle = 0;
    private String image = "Ogre_right";

    public Ogre() {
        super(30);

        setImage(Globals.getInstance().getImage(image));
        heading = Utils.directionToVector(dir, speed);
    }

    @Override
    public void step() {
        if ((stepCycle % 90 == 0) || this.isOutOfBounds()) {
            dir = -dir;
            switchImage();
            setImage(Globals.getInstance().getImage(image));
        }
        heading = Utils.directionToVector(dir, speed);
        stepCycle++;
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    private void switchImage() {
        if (image.equals("Ogre_left")) {
            image = "Ogre_right";
        } else {
            image = "Ogre_left";
        }
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
