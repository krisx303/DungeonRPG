package com.akgroup.project.world.inventory.mixtures;

import com.akgroup.project.world.inventory.IInventoryObject;

public enum Potion implements IInventoryObject {
    HEALTH(50);

    Potion(int strength) {
    }
}
