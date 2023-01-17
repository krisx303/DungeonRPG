package com.akgroup.project.world.characters.heroes;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class Mage extends AbstractHeroClass {


    public Mage() {
        super(1, 35, 70, 5);
    }

    @Override
    public int findHealth() {
        health = NumberGenerator.generateNextInt(450, 500);
        return health;
    }

    @Override
    public AbstractWeapon getWeapon() {
        return NumberGenerator.randStartingWeaponForMage();
    }

}
