package com.akgroup.project.world.map;

import com.akgroup.project.world.characters.enemies.bosses.FinalBoss;
import com.akgroup.project.world.characters.enemies.bosses.NormalBoss;
import com.akgroup.project.world.characters.enemies.weak.WeakEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    FinalBoss finalBoss;
    NormalBoss normalBoss;
    WeakEnemy weakEnemy;

    @BeforeEach
    void initialize() {
        finalBoss = new FinalBoss();
        normalBoss = new NormalBoss(1);
        weakEnemy = new WeakEnemy(2);
    }

    @Test
    void getHealth() {
        assertEquals(normalBoss.getCurrHealth(), 220);
        assertEquals(weakEnemy.getCurrHealth(), 40);
    }

}