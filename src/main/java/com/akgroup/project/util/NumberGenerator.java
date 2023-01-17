package com.akgroup.project.util;

import com.akgroup.project.world.inventory.weapon.BasicWeapon;

import java.util.Random;

public class NumberGenerator {
    private static final Random random = new Random();

    public static int generateNextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
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

}
