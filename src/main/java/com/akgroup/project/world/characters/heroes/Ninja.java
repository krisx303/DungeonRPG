package com.akgroup.project.world.characters.heroes;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;
import com.akgroup.project.world.inventory.weapon.basic.Dagger;

public class Ninja extends AbstractHeroClass {


    public Ninja() {
        super(10, 70, 1, 1);
    }

    @Override
    public int findHealth() {
        health = NumberGenerator.generateNextInt(350, 450);
        return health;
    }

    @Override
    public AbstractWeapon getWeapon() {
        return new Dagger();
    }
}
