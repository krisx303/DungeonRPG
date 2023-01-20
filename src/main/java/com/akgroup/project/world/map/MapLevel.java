package com.akgroup.project.world.map;

import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.map.object.IMapObject;

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
    private HashMap<Vector2d, IMapObject> objects;

    private final HashMap<Integer, List<Integer>> rooms;

    public MapLevel(){
        this.barriers = new HashSet<>();
        this.objects = new HashMap<>();
        this.rooms = new HashMap<>();
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
        if(!rooms.containsKey(roomID)){
            rooms.put(roomID, new ArrayList<>());
        }
        rooms.get(roomID).add(objectID);
    }

    public void addRoomDoor(Vector2d position, int roomID){
        roomDoors.put(position, roomID);
    }

    public boolean hasDoorAtPosition(int x, int y){
        return roomDoors.containsKey(new Vector2d(x, y));
    }
}
