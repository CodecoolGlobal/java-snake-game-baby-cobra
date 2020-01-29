package com.codecool.snake.entities.projectile;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class Laser extends GameEntity {
    public Laser(SnakeHead start) {
//        Globals.getInstance().addGameEntity(this);
        Point2D end = Utils.rayCastEndpoint(start.getPosition(), start.getRotate(), 500);
        Point2D position = Utils.rayCastEndpoint(start.getPosition(),start.getRotate(),500 /2)
        int width = 10;
        int height = 500;
        int pixels[] = new int[width * height];
        int alpha = 0xFF << 24;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int index = x + y * width;
                int argb = pixels[index];
                int red = 255 * x * y / (width * height);
                int green = (argb >> 8) & 0xFF;
                int blue = 0 * ((width * height) - x * y)
                        / (width * height);
                int newArgb = alpha | (red << 16) | (green << 8) | blue;
                pixels[index] = newArgb;
            }
        }
        WritableImage wi = new WritableImage(width, height);
        PixelWriter pw = wi.getPixelWriter();
        pw.setPixels(0, 0, (int) width, height, WritablePixelFormat.getIntArgbInstance(), pixels, 0, 10);

        setImage(wi);
        this.setTranslateX(wi.getWidth() / 2);
        this.setTranslateY(wi.getHeight() / 2);
        setRotate(start.getRotate());
        this.setTranslateX(-wi.getWidth() / 2);
        this.setTranslateY(-wi.getHeight() / 2);
        setX(start.getX() + (start.getImage().getWidth() / 2));
        setY(start.getY() + (start.getImage().getHeight() / 2));
        System.out.println(end.toString());
        System.out.println(start.getPosition().toString());
     }
}
