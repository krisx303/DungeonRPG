package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.world.inventory.IInventoryObject;

public enum BasicWeapon implements IInventoryObject, IWeapon {
    DAGGER(15, 20),
    KNIFE(19, 30),
    STICK(32, 50),
    ;

    BasicWeapon(int damage, int size) {
    }
}
