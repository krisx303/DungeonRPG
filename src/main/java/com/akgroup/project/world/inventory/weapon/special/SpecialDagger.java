package com.akgroup.project.world.inventory.weapon.special;

import com.akgroup.project.graphics.Sprite;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class SpecialDagger extends AbstractWeapon {

    public SpecialDagger() {
        super(60, 22);
    }

    @Override
    public String getName() {
        return "Special dagger";
    }

    @Override
    public Sprite getSprite() {
        return Sprite.DAGGER;
    }
}
