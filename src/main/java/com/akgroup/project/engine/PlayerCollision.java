package com.akgroup.project.engine;

import com.akgroup.project.world.map.WorldMap;

public class PlayerCollision {
    private int upperEdge;
    private int lowerEdge;
    private int leftEdge;
    private int rightEdge;
    private final WorldPosition position;

    private final WorldMap worldMap;

    private final int width, height;

    private int playerX, playerY;

    public PlayerCollision(WorldPosition worldPosition, WorldMap worldMap, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = worldPosition;
        this.worldMap = worldMap;
    }


    public void update(int x, int y){
        upperEdge = y /48;
        lowerEdge = (y + height)/48;
        leftEdge = x /48;
        rightEdge =(x + width)/48;
        playerX = x;
        playerY = y;
        boolean upperLeft = worldMap.hasBarrierOnPosition(leftEdge, upperEdge);
        boolean lowerLeft = worldMap.hasBarrierOnPosition(leftEdge, lowerEdge);
        boolean upperRight = worldMap.hasBarrierOnPosition(rightEdge, upperEdge);
        boolean lowerRight = worldMap.hasBarrierOnPosition(rightEdge, lowerEdge);
        int amountOfBarriers = (upperLeft ? 1 : 0) + (lowerLeft ? 1 : 0) + (upperRight ? 1 : 0) + (lowerRight ? 1 : 0);
        switch (amountOfBarriers){
            case 1 -> collisionOnCorner(upperLeft, lowerLeft, upperRight, lowerRight);
            case 2 -> collisionOnSide(upperLeft, lowerLeft, upperRight, lowerRight);
            case 3 -> collisionOnThree(upperLeft, lowerLeft, upperRight, lowerRight);
            default -> {}
        }
    }

    private void collisionOnThree(boolean upperLeft, boolean lowerLeft, boolean upperRight, boolean lowerRight) {
        if(!upperLeft){
            moveToRight();
            moveToBottom();
        }else if(!upperRight){
            moveToLeft();
            moveToBottom();
        }else if(!lowerLeft){
            moveToRight();
            moveToTop();
        }else if(!lowerRight){
            moveToLeft();
            moveToTop();
        }
    }

    private void collisionOnSide(boolean upperLeft, boolean lowerLeft, boolean upperRight, boolean lowerRight) {
        if(upperLeft){
            if(lowerLeft){
                // left side collision
                moveToLeft();
            }else if(upperRight){
                // top side collision
                moveToTop();
            }
        }
        if(lowerRight){
            if(lowerLeft){
                // bottom side collision
                moveToBottom();
            }else if(upperRight){
                // right side collision
                moveToRight();
            }
        }
    }

    private void moveToLeft(){
        position.setPositionX((leftEdge + 1) * 48 - 380);
    }

    private void moveToRight(){
        position.setPositionX((rightEdge - 1) * 48 - 372);
    }

    private void moveToTop(){
        position.setPositionY((upperEdge + 1) * 48 - 380);
    }

    private void moveToBottom(){
        position.setPositionY((lowerEdge - 1) * 48 - 372);
    }

    private void collisionOnCorner(boolean upperLeft, boolean lowerLeft, boolean upperRight, boolean lowerRight) {
        int x = playerX%48;
        int y = playerY%48;
        if(upperLeft){
            if(x <= y) moveToTop();
            else moveToLeft();

        } else if (upperRight) {
            y = 48 - (playerY%48);
            x = (playerX + 40)%48;
            if(x <= y) moveToRight();
            else moveToTop();

        } else if (lowerLeft) {
            x = 48 - (playerX%48);
            y = (playerY + 40)%48;
            if(x <= y) moveToLeft();
            else moveToBottom();

        } else if (lowerRight) {
            if(x >= y) moveToBottom();
            else moveToRight();
        }

    }
}
