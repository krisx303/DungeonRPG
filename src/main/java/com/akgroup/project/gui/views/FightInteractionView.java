package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.*;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.gui.FightDamage;
import com.akgroup.project.util.EntityDrop;
import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.characters.heroes.Fighter;
import com.akgroup.project.world.characters.heroes.Heavy;
import com.akgroup.project.world.characters.heroes.Mage;
import com.akgroup.project.world.characters.heroes.Ninja;
import com.akgroup.project.world.map.Hero;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FightInteractionView extends InteractionView {
    private Font classic, blue;

    private final int lvl;

    private boolean isPlayerClickedAttack;
    private boolean canPlayerClickAttack;
    private final boolean isItFightWithBoss;
    private boolean isFightEnded;

    private BufferedImage background;

    //    TODO chcemy ich przyjować tak? czy lepiej przekazać przy wywołaniu funkcji? (imo lepiej tu w konstruktorze)
    private Hero hero;
    private AbstractEnemyClass enemy;
    private BufferedImage playerSprite, enemySprite;

    private List<FightDamage> damages;

    public FightInteractionView(Graphics2D graphics2D, IGameObserver observer, int lvl, boolean isItFightWithBoss,
                                Hero hero, AbstractEnemyClass enemy) {
        super(graphics2D, observer);
        this.lvl = lvl;
        this.canPlayerClickAttack = true;
        this.isPlayerClickedAttack = false;
        this.isItFightWithBoss = isItFightWithBoss;
        this.hero = hero;
        this.enemy = enemy;
        this.isFightEnded = false;
        this.damages = new ArrayList<>();
        this.loadTextures();
    }


    public boolean fight() {
        if (isPlayerClickedAttack) {
            canPlayerClickAttack = false;
            isPlayerClickedAttack = false;

            heroAttack(hero, enemy);
            sleep(500);
            if (enemy.getCurrHealth() <= 0) {
                enemyDefeated(enemy, hero);
                return true;
            }
            enemyAttack(enemy, hero);
            sleep(500);
            canPlayerClickAttack = true;
        }
        if (hero.getCurrHealth() <= 0) {
            gameOver();
            return false;
        }
        return true;
    }

    private void enemyAttack(AbstractEnemyClass enemy, Hero hero) {
        int dmgGiven = NumberGenerator.countDamageGiven(enemy.getDamage(), 0, 0);
        System.out.println(dmgGiven);
        takeDamageHero(hero, dmgGiven);
    }

    private void takeDamageHero(Hero hero, int dmgToTake) {
        int value = NumberGenerator.countDamageTaken(dmgToTake, hero.getArmor(), hero.getDodge());
        System.out.println("value" + value);
        if (value > 0) {
            damages.add(new FightDamage("-" + value, 30, 400));
        } else {
            damages.add(new FightDamage("dodged", 30, 400));
        }
        hero.setCurrHealth(hero.getCurrHealth() - value);
    }

    private void heroAttack(Hero hero, AbstractEnemyClass enemy) {
        int dmgGiven = NumberGenerator.countDamageGiven(hero.getWeapon().getDamage(), hero.getCrit(), hero.getAdditionalDamage());
        damages.add(new FightDamage("-" + dmgGiven, 570, 400));
        heroHealHimself();
        System.out.println(dmgGiven);
        takeDamageEnemy(enemy, dmgGiven);
    }

    private void heroHealHimself() {
        int dmgHealed = NumberGenerator.healDamage(hero.getCurrHealth(), hero.getMaxHealth(), hero.getHeal());
        if (dmgHealed - hero.getCurrHealth() > 0) {
            damages.add(new FightDamage("+" + (dmgHealed - hero.getCurrHealth()), 30, 400));
        }
        hero.setCurrHealth(dmgHealed);
    }

    private void takeDamageEnemy(AbstractEnemyClass enemy, int dmgToTake) {
        enemy.setCurrHealth(Math.max(0, enemy.getCurrHealth() - dmgToTake));
    }

    private void gameOver() {
        isFightEnded = true;
        System.out.println("Game Over");
        observer.onGameOver();
    }

    private void enemyDefeated(AbstractEnemyClass enemy, Hero hero) {
        isFightEnded = true;
        EntityDrop entityDrop = new EntityDrop(lvl, enemy);
        updateHeroValues(entityDrop, hero);
        System.out.println("You won with enemy");
        observer.onEnemyDefeated(entityDrop);
    }

    private void updateHeroValues(EntityDrop entityDrop, Hero hero) {
        hero.addExp(entityDrop.getExp());
        hero.setMoney(hero.getMoney() + entityDrop.getMoney());
        if (entityDrop.getItem() != null && !hero.getInventory().isInventoryFull()) {
            hero.getInventory().addItemToInventory(entityDrop.getItem());
        }
    }

    private void loadTextures() {
        BufferedImage heroes = SpriteManager.getSprite(Sprite.HEROES);
        SpriteSheet heroesSprites = new SpriteSheet(heroes, 4, 16, 24, 30);
        int id = 0;

        if (hero.getCharacter() instanceof Ninja) {
            id = 0;
        } else if (hero.getCharacter() instanceof Fighter) {
            id = 1;
        } else if (hero.getCharacter() instanceof Mage) {
            id = 2;
        } else if (hero.getCharacter() instanceof Heavy) {
            id = 3;
        }
        playerSprite = heroesSprites.getSprite(1, id + 4);
        if (isItFightWithBoss) {
            enemySprite = SpriteManager.getSprite(Sprite.BOSS);
        } else {
            enemySprite = SpriteManager.getSprite(Sprite.ENEMY);
        }
    }


    public void update() {

    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void render() {
        graphics2D.drawImage(background, 0, 0, 800, 800, null);
        classic.drawStringOnCenter(FontSize.BIG_FONT, "FIGHT", 0, 50, 800);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, canPlayerClickAttack ? "Hero is attacking" : "Enemy is attacking", 0, 150, 800);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, String.valueOf(hero.getCurrHealth()), 30, 280, 200);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, String.valueOf(enemy.getCurrHealth()), 570, 280, 200);
        graphics2D.drawImage(playerSprite, 30, 310, 200, 300, null);
        graphics2D.drawImage(enemySprite, 570, 310, 200, 300, null);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "To use your attack press space", 0, 750, 800);
        damages.forEach(FightDamage::update);
        damages.forEach(d -> d.render(classic, FontSize.MEDIUM_FONT, 200));
        damages = damages.stream().filter(FightDamage::isVisible).collect(Collectors.toList());
        ;

    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_SPACE) && canPlayerClickAttack) {
            isPlayerClickedAttack = true;
            canPlayerClickAttack = false;
            fight();
        } else if (isItFightWithBoss && keyCode.equals(KeyEvent.VK_ESCAPE)) {
            if (!isFightEnded) {
                enemy.setCurrHealth(enemy.getMaxHealth());
            }
            observer.onInteractionExit();
        }
    }

    @Override
    public void initView() {
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
        this.background = SpriteManager.getSprite(Sprite.FIGHT_BACKGROUND);
    }
}
