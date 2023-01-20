package com.akgroup.project.world.map;

import com.akgroup.project.engine.WorldPosition;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
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
            currentLevel = MapLoader.loadMapLevel(levelID);

        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public void render(WorldPosition worldPosition) {
        int x = -worldPosition.getPositionX();
        int y = -worldPosition.getPositionY();
        graphics2D.drawImage(currentLevel.getBackground(), x, y, 50*48, 50*48, null);
        // render objects
        // render animations
    }

    public boolean hasBarrierOnPosition(int x, int y){
        return currentLevel.hasBarrierAtPosition(x, y);
    }

    public int getLevel() {
        return levelID;
    }

    public boolean hasDoorAtPosition(int x, int y){
        return currentLevel.hasDoorAtPosition(x, y);
    }
}