package com.akgroup.project.world.characters.heroes;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.weapon.BasicWeapon;
public class Fighter extends AbstractHeroClass {

    public Fighter() {
        super(60, 3, 1, 10);
    }

    @Override
    public int findHealth() {
        health = NumberGenerator.generateNextInt(500, 550);
        return health;
    }

    @Override
    public BasicWeapon getWeapon() {
        return BasicWeapon.STICK;
    }
}
