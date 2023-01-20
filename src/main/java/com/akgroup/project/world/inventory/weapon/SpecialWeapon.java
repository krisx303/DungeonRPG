package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.world.inventory.IInventoryObject;

public enum SpecialWeapon implements IInventoryObject, IWeapon {
    DAGGER(60, 25, "Special dagger"),
    KNIFE(55, 35, "Special knife"),
    STICK(42, 60, "Special stick");

    private final int damage;
    private final int size;
    private final String name;

    SpecialWeapon(int damage, int size, String name) {
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
