package com.akgroup.project.world.inventory.mixtures;

import com.akgroup.project.graphics.Sprite;
import com.akgroup.project.world.inventory.IInventoryObject;

public enum Potion implements IInventoryObject {
    HEALTH(50, "heal potion");

    private final int strength;
    private final String name;

    Potion(int strength, String name) {
        this.strength = strength;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Sprite getSprite() {
        return Sprite.HEAL_POTION;
    }
}
