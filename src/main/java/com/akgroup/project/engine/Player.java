package com.akgroup.project.engine;

import com.akgroup.project.graphics.*;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.map.WorldMap;
import com.akgroup.project.world.map.object.IMapObject;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    private final PlayerCollision collision;
    private final WorldPosition worldPosition;

    private IMapObject interactionObject;

    private boolean playingAnimation = false;

    public Player(WorldPosition worldPosition, WorldMap worldMap) {
        this.worldPosition = worldPosition;
        this.collision = new PlayerCollision(worldPosition, worldMap, width, height);
    }


    public void update(int horizontal, int vertical){
        int playerX = worldPosition.getPositionX() + startX;
        int playerY = worldPosition.getPositionY() + startY;
        collision.update(playerX, playerY);
        if(horizontal != 0 || vertical != 0) {
            interactionObject = collision.getObjectOnInteraction(playerX, playerY, horizontal, vertical);
        }
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
//        graphics2D.setColor(new Color(71, 15, 183));
//        graphics2D.fillRect(380, 380, 40, 40);
        BufferedImage frame = currentAnimation.getFrame();
        if(!playingAnimation){
            frame = currentAnimation.getSecondFrame();
        }
        graphics2D.drawImage(frame, startX-25, startY-30, width*2, width*2, null);
    }

    public void loadTextures(int classID) {
        BufferedImage bufferedImage = SpriteManager.getSprite(Sprite.HEROES);
        SpriteSheet spriteSheet = new SpriteSheet(bufferedImage, 4, 16, 24, 30);
        animationToDown = spriteSheet.createAnimation(classID);
        animationToRight = spriteSheet.createAnimation(classID+4);
        animationToUp = spriteSheet.createAnimation(classID+8);
        animationToLeft = spriteSheet.createAnimation(classID+12);
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

    public boolean hasInteractionObject(){
        return interactionObject != null;
    }

    public IMapObject getInteractionObject() {
        return interactionObject;
    }

    public void resetInteraction() {
        interactionObject = null;
    }
}
