package com.akgroup.project.engine;

import com.akgroup.project.world.map.WorldMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

/** Main game class. */
public class Game implements KeyListener {
    private Graphics2D graphics2D;

    private Dimension dimension;

    // private Player player;

    // private EnemyEntity actualEnemyEntity;

    private HashSet<Integer> pressedKeys;

    private WorldMap worldMap;

    public Game(Dimension dimension, Graphics2D graphics2D) {
        this.dimension = dimension;
        this.graphics2D = graphics2D;
        this.pressedKeys = new HashSet<>();
        this.worldMap = new WorldMap(this.graphics2D);
        worldMap.loadMapLevel();
    }

    public void update() {

    }

    public void input() {

    }

    public void render() {
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        worldMap.render();
        graphics2D.setColor(new Color(120, 30, 39));
        graphics2D.fillRect(0, 0, 48, 48);
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
