package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;

import java.awt.*;

public abstract class InteractionView {
    protected final Graphics2D graphics2D;

    protected final IGameObserver observer;

    protected InteractionView(Graphics2D graphics2D, IGameObserver observer) {
        this.graphics2D = graphics2D;
        this.observer = observer;
        initView();
    }

    public abstract void render();

    public abstract void onKeyClicked(Integer keyCode);

    public abstract void initView();
}