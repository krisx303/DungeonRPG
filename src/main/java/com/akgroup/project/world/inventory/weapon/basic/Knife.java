package com.akgroup.project.world.inventory.weapon.basic;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.IInventoryObject;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class Knife extends AbstractWeapon {
    public Knife() {
        super(NumberGenerator.generateNextInt(17, 20), 30);
    }
    @Override
    public String toString() {
        return "Knife{ damage: " + getDamage() + ", size: " + getSize() +"}";
    }

    @Override
    public String getName() {
        return "knife";
    }
}
