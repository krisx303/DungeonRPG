package com.akgroup.project.world.characters.heroes;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class Mage extends AbstractHeroClass {


    public Mage(int crit, int dodge, int heal, int armor) {
        super(1, 35, 70, 5);
    }

    @Override
    int findHealth() {
        return NumberGenerator.generateNextInt(450, 500);
    }

    @Override
    public AbstractWeapon getWeapon() {
        return NumberGenerator.randStartingWeaponForMage();
    }

}
