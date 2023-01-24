package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ChestNeedsKeyView extends InteractionView {
    private final Font classic;

    public ChestNeedsKeyView(Graphics2D graphics2D, IGameObserver observer) {
        super(graphics2D, observer);
        this.classic = FontManager.getManager().getClassic();
    }

    @Override
    public void render() {
        classic.drawStringOnCenter(FontSize.BIG_FONT, "You need key", 0, 200, 800);
        classic.drawStringOnCenter(FontSize.BIG_FONT, "to open the chest", 0, 250, 800);
    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_ESCAPE)) {
            observer.onInteractionExit();
        }
    }

    @Override
    public void initView() {

    }
}
