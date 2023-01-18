package com.akgroup.project.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Font extends SpriteSheet {

    private final int offset;
    private final int fontSize;
    public Font(BufferedImage image, int fontSize, int offset) {
        super(image, 11, 12, 10, 10);
        this.offset = offset;
        this.fontSize = fontSize;
    }

    public void drawString(Graphics2D graphics2d, String word, int x, int y){
        drawString(graphics2d, word, x, y, this.offset);
    }

    public void drawString(Graphics2D graphics2d, String word, int x, int y, int xOffset){
        for (char c : word.toCharArray()) {
            if(c != 32){
                graphics2d.drawImage(getLetter(c), x, y, fontSize, fontSize, null);
            }
            x += xOffset;
        }
    }

    public BufferedImage getLetter(char letter){
        int x = (int) letter % cols;
        int y = (int) letter / cols;
        return getSprite(x, y);
    }

    public void drawStringOnCenter(Graphics2D graphics2D, String text, int x, int y, int width){
        drawString(graphics2D, text, x + getAverage(width, text, offset), y);
    }

    public static int getAverage(int w, String str, int offset){
        return ((w/2)-(str.length()*offset)/2);
    }
}
