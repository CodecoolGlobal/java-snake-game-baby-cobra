package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.Random;

public abstract class Enemy extends GameEntity {
    private final int damage;

    LinkedList<Point2D> entityCoordinates = new LinkedList<>();
    private static Random rnd = new Random();

    public Enemy(int damage) {
        super();
        this.damage = damage;
        for (GameEntity entity : Globals.getInstance().getGameObjects()) {
            if (!entity.equals(this)) {
                entityCoordinates.add(entity.getPosition());
            }
        }
        while (entityCoordinates.contains(this.getPosition())) {
            setMonsterSpawnCoordinates();
            System.out.println("monster spawn collision");
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setMonsterSpawnCoordinates() {
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }
}