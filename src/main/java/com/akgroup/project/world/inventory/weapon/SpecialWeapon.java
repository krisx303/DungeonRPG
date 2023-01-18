package com.akgroup.project.world.inventory.weapon;

import com.akgroup.project.world.inventory.IInventoryObject;

public enum SpecialWeapon implements IInventoryObject, IWeapon {
    DAGGER(60, 25),
    KNIFE(55, 35),
    STICK(42, 60),
    ;

    SpecialWeapon(int damage, int size) {
    }

}
