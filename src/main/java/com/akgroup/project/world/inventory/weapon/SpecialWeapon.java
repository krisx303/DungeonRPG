package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.graphics.Sprite;
import com.akgroup.project.world.inventory.IInventoryObject;

public enum SpecialWeapon implements IInventoryObject, IWeapon {
    DAGGER(60, 25, "Special dagger", Sprite.DAGGER),
    KNIFE(55, 35, "Special knife", Sprite.KNIFE),
    STICK(42, 60, "Special stick", Sprite.STICK);

    private final int damage;
    private final int size;
    private final String name;

    private final Sprite sprite;

    SpecialWeapon(int damage, int size, String name, Sprite sprite) {
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
