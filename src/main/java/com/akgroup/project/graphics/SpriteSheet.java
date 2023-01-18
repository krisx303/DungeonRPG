package com.akgroup.project.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage[][] sprites;

    private int cols, rows;

    private int width, height;

    public SpriteSheet(BufferedImage image, int cols, int rows, int width, int height){
        this.sprites = new BufferedImage[cols][rows];
        this.cols = cols;
        this.rows = rows;
        this.width = width;
        this.height = height;
    }

    public BufferedImage getSprite(int x, int y){
        return sprites[x][y];
    }
}
