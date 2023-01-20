package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.world.inventory.IInventoryObject;

public abstract class AbstractWeapon implements IInventoryObject {
    private int damage;
    private int size;

    public AbstractWeapon(int damage, int size) {
        this.damage = damage;
        this.size = size;
    }

    public int getDamage() {
        return damage;
    }

    public int getSize() {
        return size;
    }
}
