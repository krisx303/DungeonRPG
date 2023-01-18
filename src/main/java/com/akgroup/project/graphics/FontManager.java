package com.akgroup.project.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FontManager {

    private final Font classic;
    private final Font blue;
    private static FontManager INSTANCE;


    private FontManager(Font classic, Font blue){
        this.classic = classic;
        this.blue = blue;
    }

    public static void init(Graphics2D graphics2D) throws IOException {
        if(INSTANCE != null) return;
        BufferedImage fontImage = SpriteLoader.loadSprite("font/font.png");
        Font classic = new Font(fontImage, graphics2D);
        BufferedImage fontImage2 = SpriteLoader.loadSprite("font/font_blue.png");
        Font blue = new Font(fontImage2, graphics2D);
        INSTANCE = new FontManager(classic, blue);
    }

    public static FontManager getManager(){
        return INSTANCE;
    }

    public Font getClassic() {
        return classic;
    }

    public Font getBlue() {
        return blue;
    }
}
