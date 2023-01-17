package com.akgroup.project.world.map;

import com.akgroup.project.world.characters.heroes.AbstractHeroClass;
import com.akgroup.project.world.inventory.Inventory;
import com.akgroup.project.world.inventory.weapon.IWeapon;

public class Hero {
    private final Inventory inventory;
    private int health;
    private IWeapon weapon;
    private int money;

    public Hero(AbstractHeroClass character) {
        this.inventory = new Inventory();
        this.health = character.findHealth();
        this.weapon = character.getWeapon();
        this.money = 0;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public IWeapon getWeapon() {
        return weapon;
    }

    public int getMoney() {
        return money;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWeapon(IWeapon weapon) {
        this.weapon = weapon;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

