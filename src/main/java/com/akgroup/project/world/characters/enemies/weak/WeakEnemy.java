package com.akgroup.project.world.characters.enemies.weak;

import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;

public class WeakEnemy extends AbstractEnemyClass {

    public WeakEnemy(int lvl) {
        super(10 * lvl, 5, 20 * lvl, 5 * lvl);
    }
}
