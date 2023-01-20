package com.akgroup.project.engine;

import com.akgroup.project.graphics.*;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.map.WorldMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private Animation currentAnimation;
    private Animation animationToLeft;
    private Animation animationToRight;
    private Animation animationToUp;
    private Animation animationToDown;
    private final int startX = 380;
    private final int startY = 380;

    private final int width = 40;
    private final int height = 40;

    private final WorldMap map;

    private final PlayerCollision collision;

    private final WorldPosition worldPosition;
    private SpriteSheet spriteSheet;

    private boolean playingAnimation = false;

    private int direction = 0;

    public Player(WorldPosition worldPosition, WorldMap worldMap) {
        this.worldPosition = worldPosition;
        this.map = worldMap;
        this.collision = new PlayerCollision(worldPosition, worldMap, width, height);
    }


    public void update(int horizontal, int vertical){
        int playerX = worldPosition.getPositionX() + startX;
        int playerY = worldPosition.getPositionY() + startY;
        collision.update(playerX, playerY);
        if((horizontal == 0 && vertical == 0)){
            playingAnimation = false;
        }else{
            playingAnimation = true;
            if(horizontal == 1){
                currentAnimation = animationToRight;
            }
            else if(horizontal == -1){
                currentAnimation = animationToLeft;
            }
            else if(vertical == -1){
                currentAnimation = animationToUp;
            }
            else if(vertical == 1){
                currentAnimation = animationToDown;
            }
        }
    }

    public void render(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(71, 15, 183));
        graphics2D.fillRect(380, 380, 40, 40);
        BufferedImage frame = currentAnimation.getFrame();
        if(!playingAnimation){
            frame = currentAnimation.getFirstFrame();
        }
        graphics2D.drawImage(frame, startX-25, startY-30, width*2, height*2, null);
    }

    public void loadTextures() {
        BufferedImage bufferedImage = SpriteManager.getSprite(Sprite.PLAYER);
        spriteSheet = new SpriteSheet(bufferedImage, 8, 4, 32, 32);
        animationToRight = spriteSheet.createAnimation(0);
        animationToLeft = spriteSheet.createAnimation(1);
        animationToDown = spriteSheet.createAnimation(2);
        animationToUp = spriteSheet.createAnimation(3);
        currentAnimation = animationToDown;
    }

    public int getXPosition(){
        return worldPosition.getPositionX() + startX;
    }

    public int getYPosition(){
        return worldPosition.getPositionY() + startY;
    }

    public Vector2d getPosition() {
        return new Vector2d(getXPosition(), getYPosition());
    }

    public Vector2d getTilePosition(){
        return new Vector2d(getXPosition()/48, getYPosition()/48);
    }
}
