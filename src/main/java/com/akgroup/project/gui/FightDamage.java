package com.akgroup.project.gui;

import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontSize;

public class FightDamage {

    private final String text;
    private int x,y;

    public FightDamage(String text, int x, int startY) {
        this.text = text;
        this.x = x;
        this.y = startY;
    }

    public void update() {
        this.y-=5;
    }

    public void render(Font font, FontSize fontSize, int width) {
        font.drawStringOnCenter(fontSize, text, x, y, width);
    }

    public boolean isVisible() {
        return y > 210;
    }
}
