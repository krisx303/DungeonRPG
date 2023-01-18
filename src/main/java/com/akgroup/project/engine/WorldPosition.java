package com.akgroup.project.engine;

public class WorldPosition {
    private int positionX;
    private int positionY;

    public WorldPosition() {
        this(0, 0);
    }

    public WorldPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }


    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void move(float x, float y) {
        positionX += x;
        positionY += y;
    }


    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "(%d, %d)".formatted(positionX, positionY);
    }
}
