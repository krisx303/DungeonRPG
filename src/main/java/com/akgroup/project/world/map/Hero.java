package com.akgroup.project.world.map;

import com.akgroup.project.world.characters.heroes.AbstractHeroClass;
import com.akgroup.project.world.inventory.Inventory;
import com.akgroup.project.world.inventory.weapon.AbstractWeapon;

public class Hero {
    private final AbstractHeroClass character;
    private final Inventory inventory;
    private int health;
    private AbstractWeapon weapon;

    public Hero(AbstractHeroClass character, Inventory inventory) {
        this.character = character;
        this.inventory = inventory;
        this.health = character.getHealth();
        this.weapon = character.getWeapon();
    }
}
