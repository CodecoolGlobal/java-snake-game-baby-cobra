package com.codecool.snake;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SceneBuilder {

    public static GridPane createGameOverPane(int score) {
        GridPane gameOverBox = new GridPane();
        gameOverBox.setAlignment(Pos.CENTER);
        gameOverBox.setMinWidth(Globals.WINDOW_WIDTH);
        gameOverBox.setMinHeight(Globals.WINDOW_HEIGHT);
        gameOverBox.setHgap(10);
        gameOverBox.setVgap(10);
        gameOverBox.setPadding(new Insets(10,10,10,10));

        ImageView youDied = new ImageView(new Image("youdied.jpg"));
        gameOverBox.add(youDied, 0 ,0);

        Text displayScore = new Text("Score: " + score);
        displayScore.setFont(Font.font("Arial", FontWeight.NORMAL, 36));
        displayScore.setFill(Paint.valueOf("#ffffff"));
        gameOverBox.add(displayScore, 0, 1);

        GridPane.setHalignment(displayScore, HPos.CENTER);
        gameOverBox.setStyle("-fx-background-color: #000000;");

        return gameOverBox;

    }

    public static HBox createUiPane() {
        Button restartButton = new Button("Restart");
        restartButton.setFocusTraversable(false);
        restartButton.setOnMouseClicked(Globals.getInstance()::restartGame);

        Label healthLabel = new Label("Health: ");
        Label snakeHealth = new Label();

        HBox ui = new HBox();
        ui.setPadding(new Insets(15, 12, 15, 12));
        ui.setSpacing(10);
        ui.setAlignment(Pos.CENTER_RIGHT);
        ui.setStyle("-fx-border-color: #000000;" +
                "-fx-background-color: #92cbfc;");
        ui.setPrefHeight(60);
        ui.setPrefWidth(Globals.WINDOW_WIDTH);

        ui.getChildren().addAll(healthLabel, snakeHealth, restartButton);

        return ui;
    }
}
