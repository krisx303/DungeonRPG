package com.akgroup.project.engine;

import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.gui.views.CharacterInteractionView;
import com.akgroup.project.gui.views.InteractionView;
import com.akgroup.project.gui.views.ShopInteractionView;
import com.akgroup.project.util.EntityDrop;
import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.characters.heroes.*;
import com.akgroup.project.world.map.Hero;
import com.akgroup.project.world.map.WorldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashSet;

/**
 * Main game class.
 */
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
    private Shop shop;
    private Hero hero;
    private InteractionView interactionView;



    public Game(Dimension dimension, Graphics2D graphics2D) {
        this.dimension = dimension;
        this.graphics2D = graphics2D;
        this.pressedKeys = new HashSet<>();
        this.worldMap = new WorldMap(this.graphics2D);
        this.worldPosition = new WorldPosition();
        this.player = new Player(worldPosition, worldMap);
    }

    public void initGame() throws IOException {
        SpriteManager.loadSprites();
        player.loadTextures();
        worldMap.loadMapLevel();
        gameStatus = GameStatus.CHARACTER_CHOOSING;
        FontManager.init(graphics2D);
        this.interactionView = new CharacterInteractionView(graphics2D, this);
        this.shop = new Shop(1);
    }

    public void update() {
        if (gameStatus == GameStatus.IN_GAME) {
            updateGame();
        }
    }

    private int lastAskedRoomID = -1;

    private void updateGame() {
        int left = pressedKeys.contains(KeyEvent.VK_LEFT) ? -1 : 0;
        int right = pressedKeys.contains(KeyEvent.VK_RIGHT) ? 1 : 0;
        int up = pressedKeys.contains(KeyEvent.VK_UP) ? -1 : 0;
        int down = pressedKeys.contains(KeyEvent.VK_DOWN) ? 1 : 0;
        worldPosition.move((left + right) * velocity, (up + down) * velocity);
        player.update(left + right, up + down);
        Vector2d playerPosition = player.getTilePosition();
        if(worldMap.hasDoorToUnvisitedRoomAtPosition(playerPosition)) {
            int roomID = worldMap.getRoomIDAtPosition(playerPosition);
            if(roomID != lastAskedRoomID){
                System.out.println("Open dialog for room with ID: " + roomID);
                gameStatus = GameStatus.OPENED_DIALOG;
                //TODO
                //currentDialog = new RoomEnterDialog();
                lastAskedRoomID = roomID;
            }
        }else{
            lastAskedRoomID = -1;
        }
    }

    public void render() {
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        switch (gameStatus) {
            case CHARACTER_CHOOSING, SHOP -> interactionView.render();
            case IN_GAME -> renderGame();
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
        int addedLvls = hero.addExp(entityDrop.getExp());
        if (addedLvls > 0) {
            //TODO do sth
        }
        hero.setMoney(hero.getMoney() + entityDrop.getMoney());
    }

    private void keyToggledOn(Integer keyCode) {
        switch (gameStatus) {
            case CHARACTER_CHOOSING:
            case SHOP:
                interactionView.onKeyClicked(keyCode);
                break;
            case IN_GAME:
                if(keyCode.equals(KeyEvent.VK_B)){
                    gameStatus = GameStatus.SHOP;
                    interactionView = new ShopInteractionView(graphics2D, this, shop, hero);
                }
                break;
        }
    }

    private void keyToggledOff(Integer keyCode) {}

    @Override
    public void keyTyped(KeyEvent e) {}

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
        AbstractHeroClass heroClass;
        switch (classID){
            default -> heroClass = new Ninja();
            case 1 -> heroClass = new Fighter();
            case 2 -> heroClass = new Mage();
            case 3 -> heroClass = new Heavy();
        }
        this.hero = new Hero(heroClass);
        onInteractionExit();
    }

    @Override
    public void onInteractionExit() {
        gameStatus = GameStatus.IN_GAME;
        interactionView = null;
    }
}
