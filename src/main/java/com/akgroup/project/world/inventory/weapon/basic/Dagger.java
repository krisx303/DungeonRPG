package com.akgroup.project.world.inventory.weapon.basic;

import com.akgroup.project.graphics.Sprite;
import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class Dagger extends AbstractWeapon {

    public Dagger() {
        super(NumberGenerator.generateNextInt(20, 25), 18);
    }

    @Override
    public String toString() {
        return "Dagger{ damage: " + getDamage() + ", size: " + getSize() +"}";
    }

    @Override
    public String getName() {
        return "dagger";
    }

    @Override
    public Sprite getSprite() {
        return Sprite.DAGGER;
    }
}
