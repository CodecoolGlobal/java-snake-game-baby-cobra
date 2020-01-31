package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane layout = new BorderPane();
        Globals.getInstance().ui = SceneBuilder.createUiPane();

        new Game();
        layout.setTop(Globals.getInstance().ui);
        layout.setCenter(Globals.getInstance().game);

        Scene mainScene = new Scene(layout, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT +
                Globals.getInstance().ui.getPrefHeight());

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        Globals.getInstance().game.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }
}
