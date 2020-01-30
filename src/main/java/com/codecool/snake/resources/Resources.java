package com.codecool.snake.resources;

import javafx.scene.image.Image;

import java.util.HashMap;


public class Resources {
    private HashMap<String, Image> images = new HashMap<String, Image>();

    public void addImage(String name, Image image) {
        images.put(name, image);
    }

    public Image getImage(String name) {
        Image result = images.get(name);
        if(result == null) System.out.println("ERROR: Image not found: " + name);
        return result;
    }
}
