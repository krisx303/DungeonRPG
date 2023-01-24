package com.akgroup.project.world.map;

import com.akgroup.project.engine.Shop;
import com.akgroup.project.engine.WorldPosition;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.map.object.IMapObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

/** Wrapper for MapLevel. It is also responsible for rendering all map elements.*/
public class WorldMap {

    private int currentLevelID;
    private MapLevel currentLevel;
    private final HashMap<Integer, MapLevel> levels;
    private final HashMap<Integer, Shop> levelShops;
    private final Graphics2D graphics2D;

    public WorldMap(Graphics2D graphics2D){
        this.graphics2D = graphics2D;
        this.levels = new HashMap<>();
        this.levelShops = new HashMap<>();
    }


    // mockup method
    //TODO rewrite this method
    public void loadLevels() {
        currentLevelID = 1;
        try {
            for (int i = 1; i < 5; i++) {
                levels.put(i, MapLoader.loadMapLevel(i));
                levelShops.put(i, new Shop(i));
            }
            currentLevel = levels.get(currentLevelID);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new MapLoadingException(e.getMessage());
        }
    }

    public void render(WorldPosition worldPosition) {
        int x = -worldPosition.getPositionX();
        int y = -worldPosition.getPositionY();
        graphics2D.drawImage(currentLevel.getBackground(), x, y, 50*48, 50*48, null);
        currentLevel.getObjects().forEach((pos, obj) -> {
            graphics2D.drawImage(SpriteManager.getSprite(obj.getSprite()), x + 48*pos.x, y+48*pos.y, 48, 48, null);
        });
        // render objects
        // render animations
    }

    public boolean hasBarrierOnPosition(int x, int y){
        return currentLevel.hasBarrierAtPosition(x, y);
    }

    public int getCurrentLevelID() {
        return currentLevelID;
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
        return !currentLevel.hasVisitedRoom(roomID);
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
        currentLevel.addVisitedRoom(roomID);
    }

    public IMapObject getMapObjectAt(int x, int y) {
        Vector2d pos = new Vector2d(x, y);
        if(currentLevel.getObjects().containsKey(pos)){
            return currentLevel.getObjects().get(pos);
        }
        return null;
    }

    public void loadLevel(int currentLevelID) {
        this.currentLevelID = currentLevelID;
        this.currentLevel = levels.get(currentLevelID);
    }

    public Shop getShop() {
        return levelShops.get(currentLevelID);
    }

    public Vector2d getStartPos(boolean goingDown) {
        if(goingDown){
            return currentLevel.getStartPosDown();
        }else{
            return currentLevel.getStartPosUp();
        }
    }
}