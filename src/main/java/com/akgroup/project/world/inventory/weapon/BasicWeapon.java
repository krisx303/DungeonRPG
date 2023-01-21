package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.graphics.Sprite;
import com.akgroup.project.world.inventory.IInventoryObject;

public enum BasicWeapon implements IInventoryObject, IWeapon {
    DAGGER(15, 20, "Dagger", Sprite.DAGGER),
    KNIFE(19, 30, "Knife", Sprite.KNIFE),
    STICK(32, 50, "Stick", Sprite.STICK);

    private final int damage;
    private final int size;
    private final String name;

    private final Sprite sprite;

    BasicWeapon(int damage, int size, String name, Sprite sprite) {
        this.damage = damage;
        this.size = size;
        this.name = name;
        this.sprite = sprite;
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

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }


}
