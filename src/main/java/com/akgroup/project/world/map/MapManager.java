package com.akgroup.project.world.map;

import com.akgroup.project.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;



public class MapManager {

    public static MapLevel loadMapLevel(int level){
        MapLevel mapLevel = new MapLevel();
        mapLevel.setBackground(loadBackground(level));
        return mapLevel;
    }

    private static BufferedImage loadBackground(int level) {
        BufferedImage sprite;
        try {
            sprite = ImageIO.read(Main.class.getResourceAsStream("dungeon level %d.png".formatted(level)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sprite;
    }
}
