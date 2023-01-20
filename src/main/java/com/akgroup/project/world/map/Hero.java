package com.akgroup.project.world.map;

import com.akgroup.project.world.characters.heroes.AbstractHeroClass;
import com.akgroup.project.world.exp.ExpClass;
import com.akgroup.project.world.inventory.Inventory;
import com.akgroup.project.world.inventory.weapon.IWeapon;

public class Hero {

    private final AbstractHeroClass character;
    private final Inventory inventory;
    private int health;
    private IWeapon weapon;
    private int money;
    private int additionalDamage;
    private ExpClass expClass;

    public Hero(AbstractHeroClass character) {
        this.character = character;
        this.inventory = new Inventory();
        this.health = character.findHealth();
        this.weapon = character.getWeapon();
        this.money = 0;
        this.additionalDamage = 0;
        this.expClass = new ExpClass();
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

    public int getCrit() {
        return character.getCrit();
    }

    public int getArmor() {
        return character.getArmor();
    }

    public int getDodge() {
        return character.getDodge();
    }

    public int getHeal() {
        return character.getHeal();
    }

    public int getAdditionalDamage() {
        return additionalDamage;
    }

    public int getLvl() {
        return expClass.getCurrHeroLvl();
    }

    public int addExp(int newExp) {
        return expClass.increaseExp(newExp);
    }
}

