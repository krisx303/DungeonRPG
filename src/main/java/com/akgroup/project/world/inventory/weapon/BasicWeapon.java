package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.world.inventory.IInventoryObject;

public enum BasicWeapon implements IInventoryObject, IWeapon {
    DAGGER(15, 20, "dagger"),
    KNIFE(19, 30, "knife"),
    STICK(32, 50, "stick");

    private final int damage;
    private final int size;

    private final String name;

    BasicWeapon(int damage, int size, String name) {
        this.damage = damage;
        this.size = size;
        this.name = name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }


}
