package com.akgroup.project.engine;

import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.gui.views.*;
import com.akgroup.project.util.EntityDrop;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.characters.enemies.bosses.FinalBoss;
import com.akgroup.project.world.characters.enemies.bosses.NormalBoss;
import com.akgroup.project.world.characters.heroes.*;
import com.akgroup.project.world.map.Hero;
import com.akgroup.project.world.map.WorldMap;
import com.akgroup.project.world.map.object.Chest;
import com.akgroup.project.world.map.object.IMapObject;
import com.akgroup.project.world.map.object.ShopObject;
import com.akgroup.project.world.map.object.Stairs;

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
        worldMap.loadLevels();
        gameStatus = GameStatus.CHARACTER_CHOOSING;
        FontManager.init(graphics2D);
        this.interactionView = new CharacterInteractionView(graphics2D, this);
        loadLevel(1, true);
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


    private void openRoomDialog() {
        System.out.println("Open dialog for room with ID: " + lastAskedRoomID);
        gameStatus = GameStatus.OPENED_DIALOG;
        AbstractEnemyClass enemyInRoom = worldMap.getEnemyInRoom(lastAskedRoomID);
        boolean chestInRoom = worldMap.hasRoomChest(lastAskedRoomID);
        interactionView = new EnterRoomInteractionView(graphics2D, this, lastAskedRoomID, enemyInRoom, chestInRoom);
    }

    public void render() {
        if (gameStatus != GameStatus.OPENED_DIALOG) {
            graphics2D.setColor(new Color(33, 30, 39));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
        }
        if (gameStatus == GameStatus.IN_GAME) {
            renderGame();
        } else {
            interactionView.render();
        }
    }


    private void renderGame() {
        worldMap.render(worldPosition);
        player.render(graphics2D);
        if (player.hasInteractionObject()) {
            FontManager.getManager().getClassic().drawStringOnCenter(FontSize.BIG_FONT, "Interact [E]", 0, 600, 800);
        }
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
            case SHOP, FIGHT_GAME, INVENTORY, OPENED_DIALOG, ENEMY_DEFEATED, CHEST, KEY_NOT_FOUND:
                interactionView.onKeyClicked(keyCode);
                break;
            case IN_GAME:
                if (keyCode.equals(KeyEvent.VK_I)) {
                    gameStatus = GameStatus.INVENTORY;
                    interactionView = new InventoryInteractionView(graphics2D, this, hero);
                } else if (keyCode.equals(KeyEvent.VK_E)) {
                    if (player.hasInteractionObject()) {
                        IMapObject interactionObject = player.getInteractionObject();
                        if (interactionObject instanceof ShopObject) {
                            gameStatus = GameStatus.SHOP;
                            interactionView = new ShopInteractionView(graphics2D, this, worldMap.getShop(), hero);
                        } else if (interactionObject instanceof Chest) {
                            if (((Chest) interactionObject).isLocked()) {
                                if (hero.isKeyInInventory() == -1) {
                                    gameStatus = GameStatus.KEY_NOT_FOUND;
                                    interactionView = new ChestNeedsKeyView(graphics2D, this);
                                    System.out.println("You have to have a key");
                                } else {
                                    ((Chest) interactionObject).unlockChest(hero, hero.isKeyInInventory());
                                    gameStatus = GameStatus.CHEST;
                                    interactionView = new ChestView(graphics2D, this, (Chest) interactionObject, hero);
                                }
                            } else {
                                gameStatus = GameStatus.CHEST;
                                System.out.println(((Chest) interactionObject).getItem() + " " + ((Chest) interactionObject).getMoney());
                                interactionView = new ChestView(graphics2D, this, (Chest) interactionObject, hero);
                            }
                        } else if (interactionObject instanceof Stairs stairs) {
                            if (stairs.areStairsUp()) {
                                loadLevel(worldMap.getCurrentLevelID() - 1, false);
                            } else {
                                loadLevel(worldMap.getCurrentLevelID() + 1, true);
                            }
                            player.resetInteraction();
                        }
                    }
                }
                break;
        }
    }

    private void loadLevel(int levelID, boolean goingDown) {
        worldMap.loadLevel(levelID);
        Vector2d pos = worldMap.getStartPos(goingDown);
        System.out.println(pos);
        worldPosition.setPositionX(-380 + pos.x * 48 + 10);
        worldPosition.setPositionY(-380 + pos.y * 48);
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
        player.loadTextures(classID);
        onInteractionExit();
    }

    @Override
    public void onInteractionExit() {
        gameStatus = GameStatus.IN_GAME;
        interactionView = null;
    }

    @Override
    public void enterRoom(int roomID, AbstractEnemyClass enemy) {
        if (enemy != null) {
            //TODO kiedy walka z bossem (jesli będzie enemy to ez, ale nie mamy go tak łatow jak hero)
            gameStatus = GameStatus.FIGHT_GAME;
            boolean isBoss = enemy instanceof NormalBoss || enemy instanceof FinalBoss;
            System.out.println(isBoss);
            interactionView = new FightInteractionView(graphics2D, this, worldMap.getCurrentLevelID(), isBoss, hero, enemy);
        } else {
            gameStatus = GameStatus.IN_GAME;
            worldMap.markRoomAsVisited(roomID);
        }
    }

    @Override
    public void onGameOver() {

    }

    @Override
    public void onEnemyDefeated(EntityDrop drop) {
        gameStatus = GameStatus.ENEMY_DEFEATED;
        interactionView = new EntityDefeatedInteractionView(graphics2D, this, drop, hero);
    }

    @Override
    public void onEnemyDropReceived() {
        gameStatus = GameStatus.IN_GAME;
        worldMap.markRoomAsVisited(lastAskedRoomID);
    }
}
