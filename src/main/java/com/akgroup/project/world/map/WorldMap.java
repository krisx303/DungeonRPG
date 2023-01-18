package com.akgroup.project.world.map;

import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.map.object.IMapObject;

import java.awt.*;
import java.util.HashMap;

/** Wrapper for MapLevel. It is also responsible for rendering all map elements.*/
public class WorldMap {

    private Vector2d globalPosition;
    private int levelID;
    private MapLevel currentLevel;

    private Graphics2D graphics2D;

    public WorldMap(Graphics2D graphics2D){
        this.graphics2D = graphics2D;
        //TODO probably this should be read from file
        this.globalPosition = new Vector2d(0, 0);
    }


    // mockup method
    //TODO rewrite this method
    public void loadMapLevel(){
        levelID = 1;
        currentLevel = MapManager.loadMapLevel(levelID);
    }

    public void render() {
        graphics2D.drawImage(currentLevel.getBackground(), 0, 0, 50*48, 50*48, null);
        // render objects
        // render animations
    }
}