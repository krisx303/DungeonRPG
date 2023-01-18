package com.akgroup.project.graphics;

import com.akgroup.project.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {
    public static BufferedImage loadSprite(String fileName) throws IOException {
        return ImageIO.read(Main.class.getResourceAsStream(fileName));
    }

    //public static Font loadFont(String fileName) throws IOException {}
}
