package com.akgroup.project.util;

import com.akgroup.project.world.inventory.weapon.AbstractWeapon;
import com.akgroup.project.world.inventory.weapon.basic.Dagger;
import com.akgroup.project.world.inventory.weapon.basic.Knife;
import com.akgroup.project.world.inventory.weapon.basic.Stick;

import java.util.Random;

public class NumberGenerator {
    private static final Random random = new Random();

    public static int generateNextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }


    public static String itemDroppingFromEnemy(int lvl, int changesForItem) {

        if (generateNextInt(1, 100) > changesForItem) {
            return "";
        }
        if (lvl == 4 || generateNextInt(1, 100) < 15 * lvl) {
            return weaponIsDropping(lvl);
        }
        return otherItemIsDropping();

    }

    //TODO
    private static String otherItemIsDropping() {
        return "";
    }

    private static String weaponIsDropping(int lvl) {
        int randNumber = generateNextInt(1, 100);
        if (lvl < 3) {
            if (randNumber < 70) {
                return "SuperStick";
            }
            if (randNumber < 92) {
                return "SuperKnife";
            }
        } else {
            if (randNumber < 50) {
                return "SuperStick";
            }
            if (randNumber < 80) {
                return "SuperKnife";
            }
        }
        return "SuperDagger";
    }

    public static AbstractWeapon randStartingWeaponForMage() {
        int randNumber = generateNextInt(1, 6);
        if (randNumber <= 3) {
            return new Dagger();
        }
        if (randNumber <= 5) {
            return new Knife();
        }
        return new Stick();

    }

}
