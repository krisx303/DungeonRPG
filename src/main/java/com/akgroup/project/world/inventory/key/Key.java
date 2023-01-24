package com.akgroup.project.world.inventory.key;

import com.akgroup.project.graphics.Sprite;
import com.akgroup.project.world.inventory.IInventoryObject;

public class Key implements IInventoryObject {
    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public Sprite getSprite() {
        return Sprite.KEY;
    }
}
