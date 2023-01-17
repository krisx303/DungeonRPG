package com.akgroup.project.world.characters.heroes;

import com.akgroup.project.world.characters.ICharacter;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public abstract class AbstractHeroClass implements ICharacter {
    int health;
    int damage;
    //    percentage of dmg which won't be given in every attack
    int armor;
    //    3 values below are chances to crit (double dmg in one atack), dodge(don't get demage from enemies attack) and heal(get some additional health after attack)
//    those are values beetween 0 nad 100
    int crit;
    int dodge;
    int heal;

    public AbstractHeroClass(int crit, int dodge, int heal, int armor) {
        this.crit = crit;
        this.dodge = dodge;
        this.heal = heal;
        this.armor = armor;
    }

    abstract int findHealth();

    public abstract AbstractWeapon getWeapon();

    public int getHealth() {
        return health;
    }
}
