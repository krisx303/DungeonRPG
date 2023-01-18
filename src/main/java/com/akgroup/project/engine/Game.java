package com.akgroup.project.engine;

import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.util.EntityDrop;
import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.map.Hero;
import com.akgroup.project.world.map.WorldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashSet;

/** Main game class. */
public class Game implements KeyListener, IGameObserver {
    private final Graphics2D graphics2D;

    private final Dimension dimension;

    private final Player player;
    private final WorldPosition worldPosition;

    // private EnemyEntity actualEnemyEntity;

    private final HashSet<Integer> pressedKeys;

    private final WorldMap worldMap;

    private final int velocity = 3;
    private GameStatus gameStatus;

    private final CharacterPanel characterPanel;
    public Game(Dimension dimension, Graphics2D graphics2D) {
        this.dimension = dimension;
        this.graphics2D = graphics2D;
        this.pressedKeys = new HashSet<>();
        this.worldMap = new WorldMap(this.graphics2D);
        this.worldPosition = new WorldPosition();
        this.player = new Player(worldPosition, worldMap);
        this.characterPanel = new CharacterPanel(this);
    }

    public void initGame() throws IOException {
        player.loadTextures();
        worldMap.loadMapLevel();
        gameStatus = GameStatus.CHARACTER_CHOOSING;
        FontManager.init(graphics2D);
        characterPanel.init();
    }

    public void update() {
        switch (gameStatus){
            case IN_GAME -> updateGame();
            case FIGHT_GAME -> {}
        }
    }

    private void updateGame(){
        int left = pressedKeys.contains(KeyEvent.VK_LEFT) ? -1 : 0;
        int right = pressedKeys.contains(KeyEvent.VK_RIGHT) ? 1 : 0;
        int up = pressedKeys.contains(KeyEvent.VK_UP) ? -1 : 0;
        int down = pressedKeys.contains(KeyEvent.VK_DOWN) ? 1 : 0;
        worldPosition.move((left + right) * velocity, (up + down) * velocity);
        player.update();
    }

    public void render() {
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        switch (gameStatus){
            case CHARACTER_CHOOSING -> characterPanel.render(graphics2D);
            case IN_GAME -> renderGame();
            case FIGHT_GAME -> {
            }
        }
    }

    private void renderGame() {
        worldMap.render(worldPosition);
        player.render(graphics2D);
    }

    private int getHeight() {
        return dimension.height;
    }

    private int getWidth() {
        return dimension.width;
    }

    private void keyToggledOn(Integer keyCode) {
        if(gameStatus.isCharacterChoosingMenu()){
            characterPanel.onKeyClicked(keyCode);
        }
    }

    private void keyToggledOff(Integer keyCode) {}


    public boolean fight(Hero hero, AbstractEnemyClass enemy) {
        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            heroAttack(hero, enemy);
            if (enemy.getHealth() <= 0) {
                enemyDefeated(enemy, hero);
                return true;
            }
            enemyAttack(enemy, hero);
        }
        if (hero.getHealth() <= 0) {
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
        hero.setHealth(NumberGenerator.countDamageTaken(dmgToTake, hero.getArmor(), hero.getDodge()));
    }

    private void heroAttack(Hero hero, AbstractEnemyClass enemy) {
        int dmgGiven = NumberGenerator.countDamageGiven(hero.getWeapon().getDamage(), hero.getCrit(), hero.getAdditionalDamage());
        takeDamageEnemy(enemy, dmgGiven);
    }

    private void takeDamageEnemy(AbstractEnemyClass enemy, int dmgToTake) {
        enemy.setHealth(Math.max(0, enemy.getHealth() - dmgToTake));
    }

    private void gameOver() {
        System.out.println("Game Over");
    }

    private void enemyDefeated(AbstractEnemyClass enemy, Hero hero) {
        EntityDrop entityDrop = new EntityDrop(worldMap.getLevel(), enemy);
        updateHeroValues(entityDrop, hero);
        System.out.println("You won with enemy");
    }

    private void updateHeroValues(EntityDrop entityDrop, Hero hero) {
        hero.addExp(entityDrop.getExp());
        hero.setMoney(hero.getMoney() + entityDrop.getMoney());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode()))
            keyToggledOn(e.getKeyCode());
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
        keyToggledOff(e.getKeyCode());
    }

    @Override
    public void onCharacterChoose(int classID) {
        gameStatus = GameStatus.IN_GAME;
        System.out.println("chosen class ID: " + classID);
    }
}
