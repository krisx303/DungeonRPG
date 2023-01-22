package com.akgroup.project.world.map;

import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.characters.enemies.weak.WeakEnemy;
import com.akgroup.project.world.map.object.Chest;
import com.akgroup.project.world.map.object.IMapObject;
import com.akgroup.project.world.map.object.ShopObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/** Class which stores all information about loaded map */
public class MapLevel {
    private BufferedImage background;
    private BufferedImage foreground;
    private final HashSet<Vector2d> barriers;
    private final HashMap<Vector2d, Integer> roomDoors;
    private final HashMap<Integer, AbstractEnemyClass> enemies;
    private final HashMap<Integer, List<Vector2d>> roomBarriers;
    private final HashSet<Integer> roomChests;
    private final HashMap<Vector2d, IMapObject> mapObjects;

    public MapLevel(){
        this.mapObjects = new HashMap<>();
        this.roomChests = new HashSet<>();
        this.roomBarriers = new HashMap<>();
        this.enemies = new HashMap<>();
        this.barriers = new HashSet<>();
        this.roomDoors = new HashMap<>();
    }

    public void setBackground(BufferedImage image) {
        this.background = image;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public boolean hasBarrierAtPosition(int x, int y) {
        return barriers.contains(new Vector2d(x, y));
    }

    public void addBarrier(Vector2d vector) {
        barriers.add(vector);
    }

    public void addRoomObject(int roomID, int objectID) {
//        if(!roomObjects.containsKey(roomID)){
//            roomObjects.put(roomID, new ArrayList<>());
//        }
//        rooms.get(roomID).add(objectID);
    }

    public void addRoomDoor(Vector2d position, int roomID){
        roomDoors.put(position, roomID);
    }

    public boolean hasDoorAtPosition(Vector2d position){
        return roomDoors.containsKey(position);
    }

    public Integer getDoorAtPosition(Vector2d position) {
        return roomDoors.get(position);
    }

    public int getRoomForDoor(Vector2d position) {
        return roomDoors.get(position);
    }

    public void addRoomBarrier(int roomID, Vector2d position) {
        if(!roomBarriers.containsKey(roomID)){
            roomBarriers.put(roomID, new ArrayList<>());
        }
        roomBarriers.get(roomID).add(position);
        barriers.add(position);
    }

    public void removeRoomBarriers(int roomID){
        List<Vector2d> vector2ds = roomBarriers.get(roomID);
        vector2ds.forEach(barriers::remove);
    }

    public AbstractEnemyClass getEnemyInRoom(int roomID) {
        if(enemies.containsKey(roomID)){
            return enemies.get(roomID);
        }
        return null;
    }

    public boolean hasRoomChest(int roomID) {
        return roomChests.contains(roomID);
    }

    public void addWeakEnemyToRoom(int roomID, int level) {
        enemies.put(roomID, new WeakEnemy(level));
    }

    public void addChestAtPosition(int roomID, Vector2d position, Chest chest) {
        mapObjects.put(position, chest);
        roomChests.add(roomID);
        barriers.add(position);
    }

    public void addShopAtPosition(Vector2d vector2d) {
        this.mapObjects.put(vector2d, new ShopObject());
    }

    public HashMap<Vector2d, IMapObject> getObjects() {
        return mapObjects;
    }
}
