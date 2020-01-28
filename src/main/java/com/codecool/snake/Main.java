package com.codecool.snake;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Globals.getInstance().game = new Game();
        Game game = Globals.getInstance().game;

        BorderPane layout = new BorderPane();
        Globals.getInstance().ui = createUi();

        layout.setTop(Globals.getInstance().ui);
        layout.setCenter(game);

        Scene mainScene = new Scene(layout, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT +
                                                                        Globals.getInstance().ui.getPrefHeight());

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        game.start();
    }

    private HBox createUi() {
        Button restartButton = new Button("Restart");
        restartButton.setFocusTraversable(false);
        restartButton.setOnMouseClicked(Globals.getInstance()::restartGame);

        Label snakeHealth = new Label();

        HBox ui = new HBox();
        ui.setPadding(new Insets(15, 12, 15, 12));
        ui.setSpacing(10);
        ui.setStyle("-fx-border-color: #000000;" +
                "-fx-background-color: #92cbfc;");
        ui.setPrefHeight(60);
        ui.setPrefWidth(Globals.WINDOW_WIDTH);
        ui.getChildren().add(restartButton);
        ui.getChildren().add(new Label("Health: "));
        ui.getChildren().add(snakeHealth);
        HBox.setHgrow(restartButton, Priority.ALWAYS);
        return ui;
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }
}
