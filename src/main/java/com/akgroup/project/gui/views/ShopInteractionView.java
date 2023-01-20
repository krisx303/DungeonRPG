package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.engine.Shop;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.world.inventory.IInventoryObject;
import com.akgroup.project.world.map.Hero;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ShopInteractionView extends InteractionView {
    private final Shop shop;
    private Font classic, blue;
    private int actualChoice = 0;
    private Hero hero;

    public ShopInteractionView(Graphics2D graphics2D, IGameObserver observer, Shop shop, Hero hero) {
        super(graphics2D, observer);
        this.shop = shop;
        this.hero = hero;
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
    }


    public void buyItemFromShop(int itemIndex, Hero hero, Shop shop) {
        if (hero.getInventory().isInventoryFull()) {
            return;
        }
        if (shop.getValueOfiItemFromPosition(itemIndex) > hero.getMoney()) {
            return;
        }
        IInventoryObject newItem = shop.getItemFromPosition(itemIndex);
        if (newItem == null) {
            return;
        }
        hero.setMoney(hero.getMoney() - shop.getValueOfiItemFromPosition(itemIndex));
        hero.getInventory().addItemToInventory(newItem);
        shop.removeItemFromPosition(itemIndex);
    }


    @Override
    public void render() {
        classic.drawStringOnCenter(FontSize.BIG_FONT, "Shop", 0, 50, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Money: " + hero.getMoney(), 590, 10, 200);
        int x = 50;
        for (int i = 0; i < 3; i++) {
            if (i == actualChoice) {
                graphics2D.setColor(new Color(5, 119, 159));
            } else {
                graphics2D.setColor(new Color(119, 78, 0));
            }
            graphics2D.fillRect(x + 250 * i, 210, 200, 200);
            graphics2D.setColor(new Color(33, 30, 39));
            graphics2D.fillRect(x + 5 + 250 * i, 215, 190, 190);
            if (shop.getItemFromPosition(i) != null) {
                classic.drawStringOnCenter(FontSize.SMALL_FONT, shop.getItemFromPosition(i).getName(), x + 250 * i, 450, 200);
                graphics2D.drawImage(SpriteManager.getSprite(shop.getItemFromPosition(i).getSprite()), x + 25 + 250 * i, 235, 150, 150, null);
                classic.drawStringOnCenter(FontSize.SMALL_FONT, "Cost: " + shop.getValueOfiItemFromPosition(i), x + 250 * i, 500, 200);

            }
//            classic.drawStringOnCenter(FontSize.SMALL_FONT, heroesClassNames[i], i * 160 + 80, 355, 150);
        }
//        classic.drawStringOnCenter(FontSize.BIG_FONT, "Start journey", 0, 600, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "To buy item press enter on chosen one", 0, 650, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Make sure you have enough space", 0, 700, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "in inventory to buy new item", 0, 720, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Press esc to leave the shop", 0, 770, 800);
    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_RIGHT) && actualChoice < 2) {
            actualChoice++;
        }
        else if (keyCode.equals(KeyEvent.VK_LEFT) && actualChoice > 0) {
            actualChoice--;
        }
        else if (keyCode.equals(KeyEvent.VK_ESCAPE)){
            observer.onInteractionExit();
        }
    }

    @Override
    public void initView() {
        return;
    }
}
