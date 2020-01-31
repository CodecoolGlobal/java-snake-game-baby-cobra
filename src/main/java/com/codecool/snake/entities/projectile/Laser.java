package com.codecool.snake.entities.projectile;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.FadeTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;

public class Laser extends ImageView {
    public Laser(SnakeHead start, int distance) {
        WritableImage wi = getWritableImage(distance);

        Pane pane = Globals.getInstance().display.getDisplayPane();
        setImage(wi);
        Point2D position = new Point2D(start.getX() + (start.getImage().getWidth() / 2) + (wi.getWidth() / 2), start.getY() + (start.getImage().getHeight() / 2));
        Rotate rotate = new Rotate();
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
        rotate.setPivotX(position.getX());
        rotate.setPivotY(position.getY());
        rotate.setAngle(start.getRotate() + 180);
        setPosition(position);
        this.getTransforms().add(rotate);
        pane.getChildren().addAll(this);
    }

    private WritableImage getWritableImage(int distance) {
        int width = 10;
        int height = distance;
        int pixels[] = new int[width * height];
        int alpha = 0xFF << 24;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int index = x + y * width;
                int argb = pixels[index];
//                int red = 255 * x * y / (width * height);
                int red = (int)Math.pow(((-width /2)+x)*5 + 5,2);
                int green = (argb >> 8) & 0xFF;
                int blue = 0;
                int newArgb = alpha-red << 24 | (red << 16) | (green << 8) | blue;
                pixels[index] = newArgb;
            }
        }
        WritableImage wi = new WritableImage(width, height);
        PixelWriter pw = wi.getPixelWriter();
        pw.setPixels(0, 0, (int) width, height, WritablePixelFormat.getIntArgbInstance(), pixels, 0, 10);
        return wi;
    }

    public void setPosition(Point2D pos) {
        setX(pos.getX());
        setY(pos.getY());
    }

}
