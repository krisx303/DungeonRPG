package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.world.inventory.IInventoryObject;

public enum BasicWeapon implements IInventoryObject, IWeapon {
    DAGGER(15, 20),
    KNIFE(19, 30),
    STICK(32, 50);

    private final int damage;
    private final int size;

    BasicWeapon(int damage, int size) {
        this.damage = damage;
        this.size = size;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getSize() {
        return size;
    }
}
