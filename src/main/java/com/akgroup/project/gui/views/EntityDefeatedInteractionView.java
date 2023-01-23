package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.util.EntityDrop;
import com.akgroup.project.world.inventory.IInventoryObject;
import com.akgroup.project.world.map.Hero;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EntityDefeatedInteractionView extends InteractionView {
    private Font classic, blue;
    private EntityDrop entityDrop;
    private int actualChoice;
    private Hero hero;

    public EntityDefeatedInteractionView(Graphics2D graphics2D, IGameObserver observer, EntityDrop entityDrop, Hero hero) {
        super(graphics2D, observer);
        this.entityDrop = entityDrop;
        this.actualChoice = 0;
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
        this.hero = hero;
    }

    @Override
    public void render() {
        classic.drawStringOnCenter(FontSize.BIG_FONT, "YOU WON", 0, 40, 800);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "EXP: +" + entityDrop.getExp(), 0, 120, 800);
        classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "Money: +" + entityDrop.getMoney(), 0, 170, 800);
        if (entityDrop.getItem() != null) {
            classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "You received drop!", 0, 250, 800);
            classic.drawStringOnCenter(FontSize.SMALL_FONT, "Item:", 0, 300, 800);
            graphics2D.drawImage(SpriteManager.getSprite(entityDrop.getItem().getSprite()), 300, 330, 200, 200, null);
            classic.drawStringOnCenter(FontSize.SMALL_FONT, entityDrop.getItem().getName(), 0, 550, 800);
        }
        if (hero.getLvlsToAdd() > 0) {
            classic.drawStringOnCenter(FontSize.MEDIUM_FONT, "Level up!", 0, 630, 800);
            if (actualChoice == 0) {
                blue.drawStringOnCenter(FontSize.SMALL_FONT, "Add 15 to max health", 0, 680, 800);
                classic.drawStringOnCenter(FontSize.SMALL_FONT, "Add 3 damage", 0, 710, 800);
            } else {
                classic.drawStringOnCenter(FontSize.SMALL_FONT, "Add 15 to max health", 0, 680, 800);
                blue.drawStringOnCenter(FontSize.SMALL_FONT, "Add 3 damage", 0, 710, 800);
            }
            classic.drawStringOnCenter(FontSize.SMALL_FONT, "Choose option and press enter", 0, 760, 800);
        }
    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_DOWN) && actualChoice < 1) {
            actualChoice++;
        } else if (keyCode.equals(KeyEvent.VK_UP) && actualChoice > 0) {
            actualChoice--;
        } else if (keyCode.equals(KeyEvent.VK_ESCAPE)) {
            observer.onEnemyDropReceived();
        } else if (keyCode.equals(KeyEvent.VK_ENTER) && hero.getLvlsToAdd() > 0) {
            if (actualChoice == 0) {
                hero.setNewAbility("health", 15);
            } else {
                hero.setNewAbility("damage", 3);
            }

        }
    }

    @Override
    public void initView() {

    }
}
