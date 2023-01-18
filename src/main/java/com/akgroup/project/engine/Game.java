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

    // private EnemyEntity actualEnemyEntity;

    private HashSet<Integer> pressedKeys;

    private WorldMap worldMap;

    private final float acc = 0.5f;
    private final float deacc = 0.2f;
    private final float maxYSpeed = 3.0f;
    private final float maxXSpeed = 4.0f;
    private int cameraX, cameraY;

    private float dx, dy;

    public Game(Dimension dimension, Graphics2D graphics2D) {
        this.dimension = dimension;
        this.graphics2D = graphics2D;
        this.pressedKeys = new HashSet<>();
        this.worldMap = new WorldMap(this.graphics2D);
        this.cameraX = 0;
        this.cameraY = 0;
        worldMap.loadMapLevel();
    }

    public void update() {
        changeDxAndDy();
        cameraX += dx;
        cameraY += dy;
        int x = (400 - cameraX)/48;
        int y = (400 - cameraY)/48;
        checkAndRepairPosition();
    }

    private void checkAndRepairPosition() {
        // collision system demo
        int upper = (400 - cameraY - 20)/48;
        int lower = (400 - cameraY + 20)/48;
        int onLeft= (400 - cameraX - 20)/48;
        int onRight=(400 - cameraX + 20)/48;
        boolean barrierUp = worldMap.hasBarrierOnPositionVertical(400 - cameraX, upper, 20, 48);
        if(barrierUp) cameraY = 400 - (upper + 1) * 48 - 20;
        boolean barrierDown = worldMap.hasBarrierOnPositionVertical(400 - cameraX, lower, 20, 48);
        if(barrierDown) cameraY = 400 - (lower - 1) * 48 - 28;
        boolean barrierLeft = worldMap.hasBarrierOnPositionHorizontal(400 - cameraY, onLeft, 20, 48);
        if(barrierLeft) cameraX = 400 - (onLeft + 1) * 48 - 20;
        boolean barrierRight = worldMap.hasBarrierOnPositionHorizontal(400 - cameraY, onRight, 20, 48);
        if(barrierRight) cameraX = 400 - (onRight - 1) * 48 - 28;
    }


    private void changeDxAndDy() {
        boolean left = pressedKeys.contains(KeyEvent.VK_LEFT);
        boolean right = pressedKeys.contains(KeyEvent.VK_RIGHT);
        boolean up = pressedKeys.contains(KeyEvent.VK_UP);
        boolean down = pressedKeys.contains(KeyEvent.VK_DOWN);
        if(down){
            dy -= acc;
            if(dy < -maxYSpeed)
                dy = -maxYSpeed;
        }
        else {
            if(dy < 0){
                dy += deacc;
                if(dy > 0)
                    dy = 0;
            }
        }
        if(up){
            dy += acc;
            if(dy > maxYSpeed)
                dy = maxYSpeed;
        }
        else {
            if(dy > 0){
                dy -= deacc;
                if(dy < 0)
                    dy = 0;
            }
        }
        if(right){
            dx -= acc;
            if(dx < -maxXSpeed)
                dx = -maxXSpeed;
        }
        else {
            if(dx < 0){
                dx += deacc;
                if(dx > 0)
                    dx = 0;
            }
        }
        if(left){
            dx += acc;
            if(dx > maxXSpeed)
                dx = maxXSpeed;
        }
        else {
            if(dx > 0){
                dx -= deacc;
                if(dx < 0)
                    dx = 0;
            }
        }
    }

    public void render() {
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        worldMap.render(cameraX, cameraY);
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
