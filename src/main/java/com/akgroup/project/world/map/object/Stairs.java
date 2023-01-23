package com.akgroup.project.world.map.object;

import com.akgroup.project.graphics.Sprite;

public class Stairs implements IMapObject {

    private final boolean areStairsUp;

    public Stairs(boolean areStairsUp) {
        this.areStairsUp = areStairsUp;
    }

    @Override
    public void onInteraction() {

    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    public boolean areStairsUp() {
        return areStairsUp;
    }
}
