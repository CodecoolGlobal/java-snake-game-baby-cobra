package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class Snake implements Animatable {
    private float speed = 2;
    private int health = 100;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;
    private long lastFired = System.nanoTime();
    private long lastRestart = System.nanoTime();


    public Snake(Point2D position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.SPACE)) {
            if (System.nanoTime() - lastFired >= 500000000) {
                Utils.rayCastHit(1000, this.head);
                lastFired = System.nanoTime();
            }
        }
        if (InputHandler.getInstance().isKeyPressed(KeyCode.R)) {
            if (System.nanoTime() - lastRestart >= 500000000) {
                Globals.getInstance().restartGame();
                lastRestart = System.nanoTime();
            }
        }
        return turnDir;
    }


        public void addPart ( int numParts){
            GameEntity parent = getLastPart();
            Point2D position = parent.getPosition();

            for (int i = 0; i < numParts; i++) {
                SnakeBody newBodyPart = new SnakeBody(position);
                body.add(newBodyPart);
            }
            Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
        }

        public void changeHealth ( int diff){
            health += diff;
            Globals.getInstance().updateHealthOnUi(health);
        }

        public int getHealth () {
            return health;
        }

        private void checkForGameOverConditions () {
            if (head.isOutOfBounds() || health <= 0) {
                System.out.println("Game Over. Body Length: " + countBodyLength());
                Globals.getInstance().showGameOver(countBodyLength());
                Globals.getInstance().stopGame();
            }
        }

        private int countBodyLength () {
            int size = 0;
            for (GameEntity part : body.getList()) {
                size++;
            }
            return size;
        }

        private void updateSnakeBodyHistory () {
            GameEntity prev = head;
            for (GameEntity currentPart : body.getList()) {
                currentPart.setPosition(prev.getPosition());
                prev = currentPart;
            }
        }

        private GameEntity getLastPart () {
            GameEntity result = body.getLast();

            if (result != null) return result;
            return head;
        }

        public void changeSpeed ( float i){
            speed += i;
        }
    }
