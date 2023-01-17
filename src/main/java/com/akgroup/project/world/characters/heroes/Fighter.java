package com.akgroup.project.world.characters.heroes;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;
import com.akgroup.project.world.inventory.weapon.basic.Stick;

public class Fighter extends AbstractHeroClass {

    public Fighter(int crit, int dodge, int heal, int armor) {
        super(60, 3, 1, 10);
    }

    @Override
    int findHealth() {
        return NumberGenerator.generateNextInt(500, 550);
    }

    @Override
    public AbstractWeapon getWeapon() {
        return new Stick();
    }
}
