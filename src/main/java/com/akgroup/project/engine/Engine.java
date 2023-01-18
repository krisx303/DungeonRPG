package com.akgroup.project.engine;

import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.map.Hero;

public class Engine {


    public boolean fight(Hero hero, AbstractEnemyClass enemy) {
        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            heroAttack(hero);
            enemyAttack();
        }
        if (hero.getHealth() <= 0) {
            gameOver();
            return false;
        }
        return true;
    }

    private void enemyAttack() {
    }

    private void heroAttack(Hero hero) {
//        hero.getWeapon().
    }

    private void gameOver() {
        System.out.println("Game Over");
    }

}
