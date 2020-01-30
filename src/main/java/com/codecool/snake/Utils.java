package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.projectile.Laser;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), -length * Math.cos(directionInRadians));
        return heading;
    }

    /***
     * calculates distance between 2 points
     * @param p1
     * @param p2
     * @return Double distance
     */

    public static double distance(Point2D p1, Point2D p2) {
        return sqrt(pow(p1.getX() - p2.getX(), 2) + pow(p1.getY() - p2.getY(), 2));
    }


    public static boolean isIntersect(Point2D p1, Point2D p2, Point2D p3) {
        return (distance(p1, p3) + distance(p2, p3) <= distance(p1, p2)  + 1);
    }

    public static Point2D rayCastEndpoint(Point2D start, Double angle, int distance) {
        double startX = start.getX();
        double startY = start.getY();

        double endX = startX + (Math.cos(Math.toRadians(angle)) * distance);
        double endY = startY + (Math.sin(Math.toRadians(angle)) * distance);
        return new Point2D(endX, endY);
    }

    public static LinkedList<GameEntity> rayCastHit(int distance, SnakeHead head) {
        LinkedList<GameEntity> gameObjects = Globals.getInstance().getGameObjects();
        LinkedList<GameEntity> hit = new LinkedList<>();
        LinkedList<String> hits = new LinkedList<>();

        Point2D start = head.getPosition();
        Point2D end = rayCastEndpoint(head.getPosition(), head.getRotate() -90, distance);

        for (GameEntity ge : gameObjects) {
            if (isIntersect(start, end, ge.getPosition())) {
                if (!(ge instanceof SnakeBody) && !(ge instanceof SnakeHead)) {
                    hits.add(ge.getClass().toString());
                    hit.add(ge);
                }
            }
        }
        new Laser(head, distance);

        if (hit.size() > 1) {
            System.out.println("hit " + hit.size());
            System.out.println(hits);
            System.out.println("=============");
        }
        for (GameEntity ge : hit ){
            ge.destroy();
        }
        return hit;
    }


}
