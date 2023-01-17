package com.akgroup.project.world.inventory.weapon.basic;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class Dagger extends AbstractWeapon {

    public Dagger() {
        super(NumberGenerator.generateNextInt(20, 25), 18);
    }
}
