package com.akgroup.project.world.map;

import com.akgroup.project.Main;
import com.akgroup.project.graphics.SpriteLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MapManager {

    public static MapLevel loadMapLevel(int level) throws IOException {
        MapLevel mapLevel = new MapLevel();
        BufferedImage background = SpriteLoader.loadSprite("dungeon level %d.png".formatted(level));
        mapLevel.setBackground(background);
        return mapLevel;
    }
}
