package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.util.EntityDrop;

import java.awt.*;

public class EntityDefeatedInteractionView extends InteractionView {
    public EntityDefeatedInteractionView(Graphics2D graphics2D, IGameObserver game, EntityDrop drop) {
        super(graphics2D, game);
    }

    @Override
    public void render() {
        graphics2D.setColor(new Color(99, 46, 199));
        graphics2D.fillRect(0, 0, 800, 800);
    }

    @Override
    public void onKeyClicked(Integer keyCode) {

    }

    @Override
    public void initView() {

    }
}
