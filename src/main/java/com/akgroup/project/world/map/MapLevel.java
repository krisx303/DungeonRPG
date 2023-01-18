package com.akgroup.project.world.map;

import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.map.object.IMapObject;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;


/** Class which stores all information about loaded map */
public class MapLevel {
    private BufferedImage background;
    private BufferedImage foreground;
    private HashSet<Vector2d> barriers;
    private HashMap<Vector2d, IMapObject> objects;

    public MapLevel(){
        this.barriers = new HashSet<>();
        this.objects = new HashMap<>();
        //TODO to remove
        //TODO read barriers and objects from file
        barriers.add(new Vector2d(1, 1));
    }

    public void setBackground(BufferedImage image) {
        this.background = image;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public boolean hasBarrierOnPosition(int x, int y) {
        return barriers.contains(new Vector2d(x, y));
    }
}
