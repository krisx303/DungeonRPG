package com.akgroup.project.graphics;

import com.akgroup.project.engine.FontSize;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Font extends SpriteSheet {

    private final Graphics2D graphics2D;

    public Font(BufferedImage image, Graphics2D graphics2D) {
        super(image, 11, 12, 10, 10);
        this.graphics2D = graphics2D;
    }

    public void drawString(FontSize font, String word, int x, int y){
        drawString(font, word, x, y, font.offset);
    }

    public void drawString(FontSize font, String word, int x, int y, int xOffset){
        for (char c : word.toCharArray()) {
            if(c != 32){
                graphics2D.drawImage(getLetter(c), x, y, font.fontSize, font.fontSize, null);
            }
            x += xOffset;
        }
    }

    public BufferedImage getLetter(char letter){
        int x = (int) letter % cols;
        int y = (int) letter / cols;
        return getSprite(x, y);
    }

    public void drawStringOnCenter(FontSize font, String text, int x, int y, int width){
        drawString(font, text, x + getAverage(width, text, font.offset), y);
    }

    public static int getAverage(int w, String str, int offset){
        return ((w/2)-(str.length()*offset)/2);
    }
}
