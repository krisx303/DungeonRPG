package com.akgroup.project.engine;

import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.SpriteLoader;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.map.Hero;
import com.akgroup.project.world.map.WorldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;

/** Main game class. */
public class Game implements KeyListener {
    private final Graphics2D graphics2D;

    private final Dimension dimension;

    private final Player player;
    private final WorldPosition worldPosition;

    // private EnemyEntity actualEnemyEntity;

    private final HashSet<Integer> pressedKeys;

    private final WorldMap worldMap;

    private final int velocity = 3;

    private GameStatus gameStatus;
    private Font font;

    public Game(Dimension dimension, Graphics2D graphics2D) {
        this.dimension = dimension;
        this.graphics2D = graphics2D;
        this.pressedKeys = new HashSet<>();
        this.worldMap = new WorldMap(this.graphics2D);
        this.worldPosition = new WorldPosition();
        this.player = new Player(worldPosition, worldMap);
    }

    public void initGame() throws IOException {
        BufferedImage fontImage = SpriteLoader.loadSprite("font/font.png");
        font = new Font(fontImage, 32, 38);
        player.loadTextures();
        worldMap.loadMapLevel();
        gameStatus = GameStatus.CHARACTER_CHOOSING;
    }

    public void update() {
        switch (gameStatus){
            case CHARACTER_CHOOSING -> updateCharacterMenu();
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

    private void updateCharacterMenu(){

    }

    public void render() {
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        switch (gameStatus){
            case CHARACTER_CHOOSING -> renderMenu();
            case IN_GAME -> renderGame();
            case FIGHT_GAME -> {
            }
        }
    }

    private void renderGame() {
        worldMap.render(worldPosition);
        player.render(graphics2D);
    }

    private void renderMenu() {
        font.drawStringOnCenter(graphics2D, "Wybierz klase", 0, 20, 800);
        int x = 80;
        for (int i = 0; i < 4; i++) {
            graphics2D.setColor(new Color(119, 78, 0));
            graphics2D.fillRect(x+160*i, 150, 150, 250);
            graphics2D.setColor(new Color(33, 30, 39));
            graphics2D.fillRect(x+5+160*i, 155, 140, 240);
        }
    }

    private int getHeight() {
        return dimension.height;
    }

    private int getWidth() {
        return dimension.width;
    }

    private void keyToggledOn(Integer keyCode) {
        System.out.println("toggle on: " + keyCode);
        if(keyCode.equals(KeyEvent.VK_ENTER) && gameStatus == GameStatus.CHARACTER_CHOOSING){
            gameStatus = GameStatus.IN_GAME;
        }
    }

    private void keyToggledOff(Integer keyCode) {
        System.out.println("toggle off: " + keyCode);
    }


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
}
