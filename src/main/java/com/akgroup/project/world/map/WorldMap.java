package com.akgroup.project.world.map;

import com.akgroup.project.engine.WorldPosition;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.map.object.Chest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

/** Wrapper for MapLevel. It is also responsible for rendering all map elements.*/
public class WorldMap {

    private int levelID;
    private MapLevel currentLevel;

    //TODO List of enemies on this map? Maybe also HashMap

    private final Graphics2D graphics2D;

    private final HashSet<Integer> visitedRooms;

    public WorldMap(Graphics2D graphics2D){
        this.graphics2D = graphics2D;
        this.visitedRooms = new HashSet<>();
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

    public boolean hasDoorAtPosition(Vector2d position){
        return currentLevel.hasDoorAtPosition(position);
    }

    public Integer getDoorAtPosition(Vector2d position) {
        return currentLevel.getDoorAtPosition(position);
    }

    public boolean hasDoorToUnvisitedRoomAtPosition(Vector2d position) {
        if(!hasDoorAtPosition(position)) return false;
        int roomID = getRoomIDAtPosition(position);
        return !visitedRooms.contains(roomID);
    }

    public int getRoomIDAtPosition(Vector2d position) {
        return currentLevel.getRoomForDoor(position);
    }

    public AbstractEnemyClass getEnemyInRoom(int roomID) {
        return currentLevel.getEnemyInRoom(roomID);
    }

    public boolean hasRoomChest(int roomID){
        return currentLevel.hasRoomChest(roomID);
    }

    public void removeRoomBarriers(int roomID){
        currentLevel.removeRoomBarriers(roomID);
    }

    public void markRoomAsVisited(int roomID) {
        currentLevel.removeRoomBarriers(roomID);
        visitedRooms.add(roomID);
    }
}