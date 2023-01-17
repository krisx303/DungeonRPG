package com.akgroup.project.util;

import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.inventory.IInventoryObject;
import com.akgroup.project.world.inventory.mixtures.Potion;
import com.akgroup.project.world.inventory.weapon.SpecialWeapon;

public class EntityDrop {
    private int money;
    private int exp;
    private IInventoryObject item;

    public EntityDrop(int lvl, AbstractEnemyClass abstractEnemyClass) {
        this.money = abstractEnemyClass.getMoney();
        this.exp = abstractEnemyClass.getExp();
        this.item = itemDroppingFromEnemy(lvl, abstractEnemyClass.getItem());
    }


    private IInventoryObject itemDroppingFromEnemy(int lvl, int changesForItem) {

        if (NumberGenerator.generateNextInt(1, 100) > changesForItem) {
            return null;
        }
        if (lvl == 4 || NumberGenerator.generateNextInt(1, 100) < 15 * lvl) {
            return weaponIsDropping(lvl);
        }
        return otherItemIsDropping();

    }

    private IInventoryObject otherItemIsDropping() {
        return Potion.HEALTH;
    }

    private IInventoryObject weaponIsDropping(int lvl) {
        int randNumber = NumberGenerator.generateNextInt(1, 100);
        if (lvl < 3) {
            if (randNumber < 70) {
                return SpecialWeapon.STICK;
            }
            if (randNumber < 92) {
                return SpecialWeapon.KNIFE;
            }
        } else {
            if (randNumber < 50) {
                return SpecialWeapon.STICK;
            }
            if (randNumber < 80) {
                return SpecialWeapon.KNIFE;
            }
        }
        return SpecialWeapon.DAGGER;
    }
}
