package com.akgroup.project.world.characters.heroes;

import com.akgroup.project.util.NumberGenerator;

public class Heavy extends AbstractHeroClass {


    public Heavy(int crit, int dodge, int heal, int armor) {
        super(5, 2, 2, 25);
    }

    @Override
    int findHealth() {
        return NumberGenerator.generateNextInt(750, 850);
    }

    @Override
    int findDamage() {
        return NumberGenerator.generateNextInt(15, 18);
    }
}
