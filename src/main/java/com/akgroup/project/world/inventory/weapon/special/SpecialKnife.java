package com.akgroup.project.world.inventory.weapon.special;

import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class SpecialKnife extends AbstractWeapon {

    public SpecialKnife() {
        super(55, 35);
    }

    @Override
    public String getName() {
        return "Special knife";
    }
}
