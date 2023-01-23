package com.akgroup.project.util;

import com.akgroup.project.world.inventory.weapon.BasicWeapon;

import java.util.Random;

public class NumberGenerator {
    private static final Random random = new Random();

    public static int generateNextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static int generateMoney(int lvl) {
        int starting = generateNextInt(1, lvl);
        starting *= 10;
        for (int i = 0; i < lvl * 3; i++) {
            if (NumberGenerator.generateNextInt(0, 100) < 10) {
                starting++;
            }
        }
        return starting;
    }

    public static BasicWeapon randStartingWeaponForMage() {
        int randNumber = generateNextInt(1, 6);
        if (randNumber <= 3) {
            return BasicWeapon.DAGGER;
        }
        if (randNumber <= 5) {
            return BasicWeapon.KNIFE;
        }
        return BasicWeapon.STICK;

    }

    public static int countDamageTaken(int damage, int armor, int dodge) {
        if (generateNextInt(0, 100) <= dodge) {
            return 0;
        }
        return Math.toIntExact(Math.round(damage * (float) (100 - armor) / 100));
    }

    public static int countDamageGiven(int gunDamage, int critChances, int additionalDamage) {
        int crit = 1;
        if (generateNextInt(0, 100) <= critChances) {
            crit = 2;
        }
        return (gunDamage + additionalDamage) * (crit);
    }

    public static int healDamage(int currHealth, int maxHealth, int healChances) {
        if (generateNextInt(0, 100) <= healChances) {
            return Math.min(currHealth + 20, maxHealth);
        }
        return currHealth;
    }

}
