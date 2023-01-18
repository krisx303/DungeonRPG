package com.akgroup.project.world.map;

import java.awt.*;
import java.io.IOException;

/** Wrapper for MapLevel. It is also responsible for rendering all map elements.*/
public class WorldMap {

    private int levelID;
    private MapLevel currentLevel;

    //TODO List of enemies on this map? Maybe also HashMap

    private Graphics2D graphics2D;

    public WorldMap(Graphics2D graphics2D){
        this.graphics2D = graphics2D;
    }


    // mockup method
    //TODO rewrite this method
    public void loadMapLevel(){
        levelID = 1;
        try {
            currentLevel = MapManager.loadMapLevel(levelID);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void render(int cameraX, int cameraY) {
        graphics2D.drawImage(currentLevel.getBackground(), cameraX, cameraY, 50*48, 50*48, null);
        graphics2D.setColor(new Color(120, 30, 39));
        graphics2D.fillRect(cameraX+48, cameraY+48, 48, 48);
        // render objects
        // render animations
    }

    public boolean hasBarrierOnPosition(int x, int y){
        return currentLevel.hasBarrierOnPosition(x, y);
    }

    public boolean hasBarrierOnPositionVertical(int centerX, int y, int acc, int div) {
        return hasBarrierOnPosition((centerX + acc)/div, y) ||
                hasBarrierOnPosition((centerX - acc)/div, y);
    }

    public boolean hasBarrierOnPositionHorizontal(int centerY, int x, int acc, int div) {
        return hasBarrierOnPosition(x, (centerY + acc)/div) ||
                hasBarrierOnPosition(x, (centerY - acc)/div);
    }
}