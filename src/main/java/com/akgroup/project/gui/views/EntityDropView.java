package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.util.EntityDrop;
import com.akgroup.project.world.map.Hero;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EntityDropView extends InteractionView {
    private Font classic, blue;
    private EntityDrop entityDrop;
    private int actualChoice;
    private Hero hero;

    public EntityDropView(Graphics2D graphics2D, IGameObserver observer, EntityDrop entityDrop, Hero hero) {
        super(graphics2D, observer);
        this.entityDrop = entityDrop;
        this.actualChoice = 0;
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
    }

    @Override
    public void render() {
        classic.drawStringOnCenter(FontSize.BIG_FONT, "YOU WON", 0, 50, 800);

    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_DOWN) && actualChoice < 1) {
            actualChoice++;
        } else if (keyCode.equals(KeyEvent.VK_UP) && actualChoice > 0) {
            actualChoice--;
        } else if (keyCode.equals(KeyEvent.VK_ESCAPE)) {
            observer.onInteractionExit();

        }
    }

    @Override
    public void initView() {

    }
}
