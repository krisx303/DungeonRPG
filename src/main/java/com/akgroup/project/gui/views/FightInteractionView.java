package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.util.EntityDrop;
import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.map.Hero;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FightInteractionView extends InteractionView {
    private Font classic, blue;

    private final int lvl;

    private boolean isPlayerClickedAttack;
    private boolean canPlayerClickAttack;
    private final boolean isItFightWithBoss;
    private boolean isFightEnded;

    //    TODO chcemy ich przyjować tak? czy lepiej przekazać przy wywołaniu funkcji? (imo lepiej tu w konstruktorze)
    private Hero hero;
    private AbstractEnemyClass enemy;

    public FightInteractionView(Graphics2D graphics2D, IGameObserver observer, int lvl, boolean isItFightWithBoss, Hero hero, AbstractEnemyClass enemy) {
        super(graphics2D, observer);
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
        this.lvl = lvl;
        this.canPlayerClickAttack = true;
        this.isPlayerClickedAttack = false;
        this.isItFightWithBoss = isItFightWithBoss;
        this.hero = hero;
        this.enemy = enemy;
        this.isFightEnded = false;
    }


    public boolean fight(Hero hero, AbstractEnemyClass enemy) {
        while (hero.getCurrHealth() > 0 && enemy.getCurrHealth() > 0) {
            if (isPlayerClickedAttack) {
                canPlayerClickAttack = false;
                isPlayerClickedAttack = false;

                heroAttack(hero, enemy);
                if (enemy.getCurrHealth() <= 0) {
                    enemyDefeated(enemy, hero);
                    return true;
                }
//                TODO trzeba wrzucać wszystko w try???
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                enemyAttack(enemy, hero);
                canPlayerClickAttack = true;
            }
        }
        if (hero.getCurrHealth() <= 0) {
            gameOver();
            return false;
        }
        return true;
    }

    private void enemyAttack(AbstractEnemyClass enemy, Hero hero) {
        int dmgGiven = NumberGenerator.countDamageGiven(enemy.getDamage(), 0, 0);
        takeDamageHero(hero, dmgGiven);
    }

    private void takeDamageHero(Hero hero, int dmgToTake) {
        hero.setCurrHealth(NumberGenerator.countDamageTaken(dmgToTake, hero.getArmor(), hero.getDodge()));
    }

    private void heroAttack(Hero hero, AbstractEnemyClass enemy) {
        int dmgGiven = NumberGenerator.countDamageGiven(hero.getWeapon().getDamage(), hero.getCrit(), hero.getAdditionalDamage());
        takeDamageEnemy(enemy, dmgGiven);
    }

    private void takeDamageEnemy(AbstractEnemyClass enemy, int dmgToTake) {
        enemy.setCurrHealth(Math.max(0, enemy.getCurrHealth() - dmgToTake));
    }

    private void gameOver() {
        isFightEnded = true;
        System.out.println("Game Over");
    }

    private void enemyDefeated(AbstractEnemyClass enemy, Hero hero) {
        isFightEnded = true;
        EntityDrop entityDrop = new EntityDrop(lvl, enemy);
        updateHeroValues(entityDrop, hero);
        System.out.println("You won with enemy");
    }

    private void updateHeroValues(EntityDrop entityDrop, Hero hero) {
        int addedLvls = hero.addExp(entityDrop.getExp());
        if (addedLvls > 0) {
            //TODO do sth
        }
        hero.setMoney(hero.getMoney() + entityDrop.getMoney());
    }

    @Override
    public void render() {
        classic.drawStringOnCenter(FontSize.BIG_FONT, "FIGHT", 0, 50, 800);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "Hero/enemy is attacking", 0, 150, 800);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "250", 30, 280, 200);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "1250", 570, 280, 200);
        graphics2D.setColor(new Color(119, 78, 0));
        graphics2D.fillRect(30, 310, 200, 300);
        graphics2D.fillRect(570, 310, 200, 300);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "To use your attack press [???]", 0, 750, 800);
    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_SPACE) && canPlayerClickAttack) {
            isPlayerClickedAttack = true;
            canPlayerClickAttack = false;
        } else if (!isItFightWithBoss && keyCode.equals(KeyEvent.VK_ESCAPE)) {
            if (!isFightEnded) {
                enemy.setCurrHealth(enemy.getMaxHealth());
            }
            observer.onInteractionExit();
        }
    }

    @Override
    public void initView() {

    }
}
