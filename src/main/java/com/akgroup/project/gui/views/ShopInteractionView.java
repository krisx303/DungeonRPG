package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.engine.Shop;

import java.awt.*;

public class ShopInteractionView extends InteractionView {
    private final Shop shop;

    public ShopInteractionView(Graphics2D graphics2D, IGameObserver observer, Shop shop) {
        super(graphics2D, observer);
        this.shop = shop;
        this.shop.randItems();
    }

    @Override
    public void render() {

    }

    @Override
    public void onKeyClicked(Integer keyCode) {

    }

    @Override
    public void initView() {

    }
}
