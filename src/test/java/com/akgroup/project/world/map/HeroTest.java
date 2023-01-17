package com.akgroup.project.world.map;

import com.akgroup.project.world.characters.heroes.Fighter;
import com.akgroup.project.world.characters.heroes.Heavy;
import com.akgroup.project.world.characters.heroes.Mage;
import com.akgroup.project.world.characters.heroes.Ninja;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    Hero ninja;
    Hero fighter;
    Hero heavy;
    Hero mage;

    @BeforeEach
    void initialize() {
        ninja = new Hero(new Ninja());
        fighter = new Hero(new Fighter());
        heavy = new Hero(new Heavy());
        mage = new Hero(new Mage());
    }

    @Test
    void getWeapon() {
        System.out.println(ninja.getWeapon());
        System.out.println(fighter.getWeapon());
        System.out.println(heavy.getWeapon());
        System.out.println(mage.getWeapon());
    }

    @Test
    void getHealth() {
        System.out.println(mage.getHealth());
        assertTrue(mage.getHealth() >= 450 && mage.getHealth() <= 500);
    }
}