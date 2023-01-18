package com.akgroup.project.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private final BufferedImage image;
    private final BufferedImage[][] sprites;

    protected final int cols, rows;

    protected final int width, height;

    public SpriteSheet(BufferedImage image, int cols, int rows, int width, int height){
        this.sprites = new BufferedImage[rows][cols];
        this.image = image;
        this.cols = cols;
        this.rows = rows;
        this.width = width;
        this.height = height;
        loadSpriteArray();
    }

    public void loadSpriteArray(){
        for (int y = 0; y<rows; y++){
            for (int x = 0; x<cols; x++){
                sprites[y][x] = getSubSprite(x, y);
            }
        }
    }

    public BufferedImage getSprite(int x, int y){
        return sprites[y][x];
    }

    private BufferedImage getSubSprite(int x, int y){
        return image.getSubimage(x*width, y*height, width, height);
    }


    public Animation createAnimation(int rowIndex){
        return new Animation(sprites[rowIndex]);
    }
}
