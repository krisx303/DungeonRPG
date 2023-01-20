package com.akgroup.project.world.inventory.weapon.basic;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class Stick extends AbstractWeapon {

    public Stick() {
        super(NumberGenerator.generateNextInt(32, 36), 50);
    }

    @Override
    public String toString() {
        return "Stick{ damage: " + getDamage() + ", size: " + getSize() +"}";
    }

    @Override
    public String getName() {
        return "stick";
    }
}
