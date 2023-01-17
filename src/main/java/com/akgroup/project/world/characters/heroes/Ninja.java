package com.akgroup.project.world.characters.heroes;

import com.akgroup.project.util.NumberGenerator;

public class Ninja extends AbstractHeroClass {


    public Ninja() {
        super(10, 70, 1, 1);
    }

    @Override
    int findHealth() {
        return NumberGenerator.generateNextInt(350, 450);
    }

    @Override
    int findDamage() {
        return NumberGenerator.generateNextInt(20, 25);
    }
}
