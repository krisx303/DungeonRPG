package com.akgroup.project.world.map;

import com.akgroup.project.world.characters.heroes.AbstractHeroClass;
import com.akgroup.project.world.exp.ExpClass;
import com.akgroup.project.world.inventory.Inventory;
import com.akgroup.project.world.inventory.mixtures.Potion;
import com.akgroup.project.world.inventory.weapon.IWeapon;

public class Hero {

    private final AbstractHeroClass character;
    private final Inventory inventory;
    private int currHealth;
    private int maxHealth;
    private IWeapon weapon;
    private int money;
    private int additionalDamage;
    private ExpClass expClass;

    public Hero(AbstractHeroClass character) {
        this.character = character;
        this.inventory = new Inventory();
        this.currHealth = character.findHealth();
        this.weapon = character.getWeapon();
        this.money = 100;
        this.additionalDamage = 0;
        this.expClass = new ExpClass();
        this.maxHealth = currHealth;
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

    public int getCurrHealth() {
        return currHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrHealth(int currHealth) {
        this.currHealth = currHealth;
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

    public int getLvlsToAdd() {
        return expClass.getLvlPointsToAdd();
    }

    public int addExp(int newExp) {
        return expClass.increaseExp(newExp);
    }

    public void healFromPotion(Potion potion) {
        currHealth = Math.min(currHealth + potion.getStrength(), maxHealth);
    }

    public void setNewAbility(String option, int value) {
        switch (option) {
            case "damage" -> additionalDamage += value;
            case "health" -> maxHealth += value;
        }
        expClass.usePointLvl();
    }

    public AbstractHeroClass getCharacter() {
        return character;
    }

}

