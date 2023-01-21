package com.akgroup.project.world.characters.enemies;

import com.akgroup.project.world.characters.ICharacter;

public abstract class AbstractEnemyClass implements ICharacter {
    private final int money;
    //    exp dropping after kill this enemy
    private final int exp;
    //    chances (0-100) that item will be dropped after killing enemy
    private final int item;
    private int currHealth;
    private final int maxHealth;
    private final int damage;

    public AbstractEnemyClass(int money, int exp, int item, int maxHealth, int damage) {
        this.money = money;
        this.exp = exp;
        this.item = item;
        this.currHealth = maxHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
    }

    public int getCurrHealth() {
        return currHealth;
    }

    public int getMoney() {
        return money;
    }

    public int getExp() {
        return exp;
    }

    public int getItem() {
        return item;
    }

    public int getDamage() {
        return damage;
    }

    public void setCurrHealth(int currHealth) {
        this.currHealth = currHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
