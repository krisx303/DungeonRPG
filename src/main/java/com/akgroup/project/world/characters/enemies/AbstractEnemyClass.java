package com.akgroup.project.world.characters.enemies;

import com.akgroup.project.world.characters.ICharacter;

public abstract class AbstractEnemyClass implements ICharacter {
    //    exp dropping after kill this enemy
    private final int exp;
    //    chances (0-100) that item will be dropped after killing enemy
    private final int item;
    private final int health;
    private final int damage;

    public AbstractEnemyClass(int exp, int item, int health, int damage) {
        this.exp = exp;
        this.item = item;
        this.health = health;
        this.damage = damage;
    }

}
