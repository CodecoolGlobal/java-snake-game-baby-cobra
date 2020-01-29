package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;

    private LinkedList<GameEntity> gameObjects = new LinkedList<GameEntity>();

    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    public void restartGame(MouseEvent mouseEvent) {
        Globals.getInstance().stopGame();
        Globals.getInstance().display.clear();
        Globals.getInstance().game.init();
        Globals.getInstance().startGame();
    }

    private Globals() {
        // singleton needs the class to have private constructor
    }

    public LinkedList<GameEntity> getGameObjects() {
        return this.gameObjects;
    }

    public void addGameEntity(GameEntity entity){
        this.gameObjects.add(entity);
    }

    public void removeGameEntity(GameEntity gameEntity){
        this.gameObjects.remove(gameEntity);
    }

}
