package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.world.inventory.IInventoryObject;

public enum SpecialWeapon implements IInventoryObject, IWeapon {
    DAGGER(60, 25),
    KNIFE(55, 35),
    STICK(42, 60);

    private final int damage;
    private final int size;

    SpecialWeapon(int damage, int size) {
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
