package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;

import java.util.List;

public class GameLoop {
    private Snake snake;
    private boolean running = false;
    private boolean runningForEnemies = true;
    private int pauseForFrames;

    public GameLoop(Snake snake) {
        this.snake = snake;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void pauseEnemies(int pauseForFrames) {
        runningForEnemies = false;
        this.pauseForFrames = pauseForFrames;
    }

    public void startEnemies() {
        runningForEnemies = true;
    }

    public void step() {
        if (running) {
            snake.step();
            enemyMovementHandler();
            checkCollisions();
        }
        Globals.getInstance().display.frameFinished();
    }

    public void enemyMovementHandler() {
        if (runningForEnemies) {
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
        } else {
            pauseForFrames--;
            if (pauseForFrames == 0) {
                startEnemies();
            }
        }
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable) {
                        if (objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())) {
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
