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
//        Globals.getInstance().addGameEntity(this);

//        Point2D position = Utils.rayCastEndpoint(new Point2D(0, 0), start.getRotate(), 500 / 2);

        int width = 10;
        int height = distance;
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



        Pane pane = Globals.getInstance().display.getDisplayPane();
//        pane.setRotate(-start.getRotate());
        setImage(wi);
        Point2D position = new Point2D(start.getX()+ (start.getImage().getWidth() / 2 ) + (wi.getWidth() /2),start.getY() + (start.getImage().getHeight() /2));
//        setTranslateX(start.getX());
//        setTranslateY(start.getY());
//        setRotate(start.getRotate());
        Rotate rotate = new Rotate();
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
//        rotate.setPivotX(start.getX()+ (start.getImage().getWidth() / 2 ));
//        rotate.setPivotY(start.getY());
        rotate.setPivotX(position.getX());
        rotate.setPivotY(position.getY());
        rotate.setAngle(start.getRotate() + 180);
//        setPosition(start.getPosition());
        setPosition(position);
//        setX(start.getX() + (start.getImage().getWidth() / 2 ));
//        setY(start.getY() + start.getImage().getHeight());
        this.getTransforms().add(rotate);
//        pane.setRotate(+start.getRotate());
        pane.getChildren().add(this);
//        System.out.println("this: " + getX() + " - " + getY());
//        System.out.println("head " + start.getPosition().getX() + " - " + start.getY());
    }

    public void setPosition(Point2D pos) {
        setX(pos.getX());
        setY(pos.getY());
    }

}
