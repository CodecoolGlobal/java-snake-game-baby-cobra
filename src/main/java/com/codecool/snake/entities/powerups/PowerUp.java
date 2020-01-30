package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;

import java.util.Random;

public abstract class PowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public PowerUp(String imageName) {
        setImage(Globals.getInstance().getImage(imageName));

        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH - this.getImage().getWidth()));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT - this.getImage().getHeight()
                                                        - Globals.getInstance().ui.getPrefHeight()));
    }

}
