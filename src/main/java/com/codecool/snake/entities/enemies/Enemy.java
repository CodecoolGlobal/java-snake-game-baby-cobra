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
        Globals.getInstance().removeGameEntity(this);
        for (GameEntity entity : Globals.getInstance().getGameObjects()) {
            entityCoordinates.add(entity.getPosition());
        }
        Point2D newSpawnCoordinate = createSpawnCoordinate();
        while (entityCoordinates.contains(newSpawnCoordinate)) {
            newSpawnCoordinate = createSpawnCoordinate();
            System.out.println("monster spawn collision");
        }
        setMonsterSpawnCoordinates(newSpawnCoordinate);
        Globals.getInstance().addGameEntity(this);

    }

    public int getDamage() {
        return damage;
    }

    public Point2D createSpawnCoordinate() {
        double x = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double y = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        return new Point2D(x, y);
    }

    public static void destroyAllEnemies() {
        for (GameEntity gameEntity: Globals.getInstance().display.getObjectList()) {
            if (gameEntity instanceof Enemy) {
                gameEntity.destroy();
            }
        }
    }

    public void setMonsterSpawnCoordinates(Point2D spawnCoordinates) {
        setX(spawnCoordinates.getX());
        setY(spawnCoordinates.getY());
    }
}