package com.akgroup.project.engine;

import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.gui.views.*;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.characters.heroes.*;
import com.akgroup.project.world.map.Hero;
import com.akgroup.project.world.map.WorldMap;
import com.akgroup.project.world.map.object.Chest;

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
        if (worldMap.hasDoorToUnvisitedRoomAtPosition(playerPosition)) {
            int roomID = worldMap.getRoomIDAtPosition(playerPosition);
            if (roomID != lastAskedRoomID) {
                lastAskedRoomID = roomID;
                openRoomDialog();
            }
        } else {
            lastAskedRoomID = -1;
        }
    }


    private void openRoomDialog(){
        System.out.println("Open dialog for room with ID: " + lastAskedRoomID);
        gameStatus = GameStatus.OPENED_DIALOG;
        AbstractEnemyClass enemyInRoom = worldMap.getEnemyInRoom(lastAskedRoomID);
        boolean chestInRoom = worldMap.hasRoomChest(lastAskedRoomID);
        interactionView = new EnterRoomInteractionView(graphics2D, this, lastAskedRoomID, enemyInRoom, chestInRoom);
    }

    public void render() {
        if(gameStatus != GameStatus.OPENED_DIALOG){
            graphics2D.setColor(new Color(33, 30, 39));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
        }
        switch (gameStatus) {
            case CHARACTER_CHOOSING, SHOP, FIGHT_GAME, INVENTORY, OPENED_DIALOG -> interactionView.render();
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


    private void keyToggledOn(Integer keyCode) {
        switch (gameStatus) {
            case CHARACTER_CHOOSING:
            case SHOP, FIGHT_GAME, INVENTORY, OPENED_DIALOG:
                interactionView.onKeyClicked(keyCode);
                break;
            case IN_GAME:
                if (keyCode.equals(KeyEvent.VK_B)) {
                    gameStatus = GameStatus.SHOP;
                    interactionView = new ShopInteractionView(graphics2D, this, shop, hero);
                } else if (keyCode.equals(KeyEvent.VK_I)) {
                    gameStatus = GameStatus.INVENTORY;
                    interactionView = new InventoryInteractionView(graphics2D, this, hero);
                }
                break;
        }
    }

    private void keyToggledOff(Integer keyCode) {
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
        AbstractHeroClass heroClass;
        switch (classID) {
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

    @Override
    public void enterRoom(int roomID, AbstractEnemyClass enemy) {
        if(enemy != null){
            //TODO kiedy walka z bossem (jesli będzie enemy to ez, ale nie mamy go tak łatow jak hero)
            gameStatus = GameStatus.FIGHT_GAME;
            interactionView = new FightInteractionView(graphics2D, this, worldMap.getLevel(), false, hero, enemy);
        }else{
            gameStatus = GameStatus.IN_GAME;
            worldMap.markRoomAsVisited(roomID);
        }
    }
}
