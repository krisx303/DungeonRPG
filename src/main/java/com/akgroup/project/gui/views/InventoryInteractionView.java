package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.world.inventory.IInventoryObject;
import com.akgroup.project.world.inventory.mixtures.Potion;
import com.akgroup.project.world.inventory.weapon.BasicWeapon;
import com.akgroup.project.world.inventory.weapon.IWeapon;
import com.akgroup.project.world.map.Hero;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InventoryInteractionView extends InteractionView {

    private Font classic, blue;
    private int actualChoice = 0;
    private Hero hero;

    public InventoryInteractionView(Graphics2D graphics2D, IGameObserver observer, Hero hero) {
        super(graphics2D, observer);
        this.hero = hero;
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
    }

    @Override
    public void render() {
        classic.drawStringOnCenter(FontSize.BIG_FONT, "Inventory", 0, 50, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Money: " + hero.getMoney(), 590, 10, 200);
        graphics2D.setColor(new Color(119, 78, 0));
        graphics2D.fillRect(50, 150, 190, 190);
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(55, 155, 180, 180);
        graphics2D.drawImage(SpriteManager.getSprite(((IInventoryObject) hero.getWeapon()).getSprite()), 55, 155, 180, 180, null);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, ((IInventoryObject) hero.getWeapon()).getName(), 50, 350, 190);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Damage: " + hero.getWeapon().getDamage(), 50, 380, 190);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "Additional damage: " + hero.getAdditionalDamage(), 250, 150, 550);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "Health: " + hero.getCurrHealth() + "/" + hero.getMaxHealth() + "hp", 300, 200, 500);

        int x = 0;
        for (int i = 0; i < 10; i++) {
            if (i == actualChoice) {
                graphics2D.setColor(new Color(5, 119, 159));
                if (hero.getInventory().getItemFromPosition(i) != null) {
                    classic.drawStringOnCenter(FontSize.MEDIUM_FONT, hero.getInventory().getItemFromPosition(i).getName(), 450, 350, 200);
                    if (hero.getInventory().getItemFromPosition(i) instanceof Potion) {
                        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Heal: " + ((Potion) hero.getInventory().getItemFromPosition(i)).getStrength() + " hp", 450, 380, 200);
                    } else if (hero.getInventory().getItemFromPosition(i) instanceof IWeapon) {
                        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Damage: " + ((IWeapon) hero.getInventory().getItemFromPosition(i)).getDamage(), 450, 380, 200);
                    }
                }
            } else {
                graphics2D.setColor(new Color(119, 78, 0));
            }
            graphics2D.fillRect(x + 80 * i, 450, 80, 80);
            graphics2D.setColor(new Color(33, 30, 39));
            graphics2D.fillRect(x + 5 + 80 * i, 455, 70, 70);
            if (hero.getInventory().getItemFromPosition(i) != null) {
                graphics2D.drawImage(SpriteManager.getSprite(hero.getInventory().getItemFromPosition(i).getSprite()), x + 7 + 80 * i, 458, 65, 65, null);
            }
        }
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "To remove item press r on chosen one", 0, 600, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "To change your weapon press ", 0, 650, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "enter on chosen one", 0, 670, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "To use potion press enter on chosen one", 0, 720, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Press esc to leave the shop", 0, 770, 800);
    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_RIGHT) && actualChoice < 9) {
            actualChoice++;
        } else if (keyCode.equals(KeyEvent.VK_LEFT) && actualChoice > 0) {
            actualChoice--;
        } else if (keyCode.equals(KeyEvent.VK_ESCAPE)) {
            observer.onInteractionExit();
        } else if (keyCode.equals(KeyEvent.VK_R)) {
            hero.getInventory().removeItemFromIndex(actualChoice);
        } else if (keyCode.equals(KeyEvent.VK_ENTER)) {
            IInventoryObject item = hero.getInventory().getItemFromPosition(actualChoice);
            hero.getInventory().removeItemFromIndex(actualChoice);
            if (item instanceof Potion) {
                hero.healFromPotion((Potion) item);
            } else if (item instanceof IWeapon) {
                hero.getInventory().addItemToInventory((IInventoryObject) hero.getWeapon());
                hero.setWeapon((IWeapon) item);
            }
        } else if (keyCode.equals(KeyEvent.VK_P)) {
//            tmp for testing
            hero.setCurrHealth(hero.getCurrHealth()-30);
            hero.getInventory().addItemToInventory(Potion.HEALTH);
            hero.getInventory().addItemToInventory(BasicWeapon.DAGGER);
        }

    }

    @Override
    public void initView() {

    }
}
