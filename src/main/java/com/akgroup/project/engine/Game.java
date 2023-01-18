package com.akgroup.project.engine;

import com.akgroup.project.world.map.WorldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

/** Main game class. */
public class Game implements KeyListener {
    private final Graphics2D graphics2D;

    private final Dimension dimension;

    // private Player player;

    private final PlayerCollision playerCollision;

    private final WorldPosition worldPosition;

    // private EnemyEntity actualEnemyEntity;

    private final HashSet<Integer> pressedKeys;

    private final WorldMap worldMap;

    private final float velocity = 2.0f;

    public Game(Dimension dimension, Graphics2D graphics2D) {
        this.dimension = dimension;
        this.graphics2D = graphics2D;
        this.pressedKeys = new HashSet<>();
        this.worldMap = new WorldMap(this.graphics2D);
        this.worldPosition = new WorldPosition();
        this.playerCollision = new PlayerCollision(worldPosition, worldMap, 40, 40);
        worldMap.loadMapLevel();
    }

    public void update() {
        checkAndRepairPosition();
    }

    private void checkAndRepairPosition() {
        int left = pressedKeys.contains(KeyEvent.VK_LEFT) ? -1 : 0;
        int right = pressedKeys.contains(KeyEvent.VK_RIGHT) ? 1 : 0;
        int up = pressedKeys.contains(KeyEvent.VK_UP) ? -1 : 0;
        int down = pressedKeys.contains(KeyEvent.VK_DOWN) ? 1 : 0;
        int vertical = up + down;
        int horizontal = left + right;
        worldPosition.move(horizontal * velocity, vertical * velocity);
        int playerX = worldPosition.getPositionX() + 380;
        int playerY = worldPosition.getPositionY() + 380;
        playerCollision.update(playerX, playerY);
    }

    public void render() {
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        worldMap.render(worldPosition);
        graphics2D.setColor(new Color(71, 15, 183));
        graphics2D.fillRect(380, 380, 40, 40);
    }

    private int getHeight() {
        return dimension.height;
    }

    private int getWidth() {
        return dimension.width;
    }

    private void keyToggledOn(Integer keyCode) {
        System.out.println("toggle on: " + keyCode);
    }

    private void keyToggledOff(Integer keyCode) {
        System.out.println("toggle off: " + keyCode);
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
