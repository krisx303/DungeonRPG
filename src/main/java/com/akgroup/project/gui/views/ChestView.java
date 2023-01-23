package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.world.map.Hero;
import com.akgroup.project.world.map.object.Chest;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ChestView extends InteractionView {

    private final Font classic, blue;
    private Chest chest;
    private Hero hero;

    public ChestView(Graphics2D graphics2D, IGameObserver observer, Chest chest, Hero hero) {
        super(graphics2D, observer);
        this.hero = hero;
        this.chest = chest;
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
    }

    @Override
    public void render() {
        classic.drawStringOnCenter(FontSize.BIG_FONT, "CHEST", 0, 50, 800);
        graphics2D.setColor(new Color(119, 78, 0));
        graphics2D.fillRect(300, 230, 200, 200);
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(305, 235, 190, 190);
        if (chest.getItem() != null || chest.getMoney() > 0) {
            if (chest.getMoney() > 0) {
                classic.drawStringOnCenter(FontSize.SMALL_FONT, chest.getMoney() + " money", 300, 250, 200);
            } else {
                graphics2D.drawImage(SpriteManager.getSprite(chest.getItem().getSprite()), 310, 240, 180, 180, null);
            }
        }
        if (chest.getMoney() > 0 || chest.getItem() != null) {
            classic.drawStringOnCenter(FontSize.SMALL_FONT, "Press enter to add treasure", 0, 650, 800);
            classic.drawStringOnCenter(FontSize.SMALL_FONT, "to your inventory", 0, 670, 800);
        }
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Press esc to leave", 0, 710, 800);
    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_ESCAPE)) {
            observer.onInteractionExit();
        } else if (keyCode.equals(KeyEvent.VK_ENTER)) {
            if (chest.getMoney() > 0 || chest.getItem() != null) {
                chest.getItemFromChest(hero);
            }
        }
    }

    @Override
    public void initView() {

    }
}
