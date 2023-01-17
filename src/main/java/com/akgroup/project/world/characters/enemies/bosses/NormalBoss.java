package com.akgroup.project.world.characters.enemies.bosses;

import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;

public class NormalBoss extends AbstractEnemyClass {

    public NormalBoss(int lvl) {
        super(50, 40 * lvl, 70, 220 * lvl, 20 * lvl);
    }
}
