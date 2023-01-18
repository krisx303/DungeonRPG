package com.akgroup.project.engine;

import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.SpriteLoader;
import com.akgroup.project.graphics.SpriteSheet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CharacterPanel {
    private SpriteSheet heroesSprites;

    private Font classic, blue;

    private int actualChoice = 0;

    private final String[] heroesClassNames = new String[]{"Ninja", "Fighter", "Mage","Heavy"};

    private IGameObserver observer;

    public CharacterPanel(IGameObserver observer){
        this.observer = observer;
    }
    public void init() throws IOException {
        BufferedImage heroes = SpriteLoader.loadSprite("entity/heroes.png");
        heroesSprites = new SpriteSheet(heroes, 4, 4, 24, 30);
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
    }


    public void render(Graphics2D graphics2D){
        classic.drawStringOnCenter(FontSize.BIG_FONT, "Select class", 0, 20, 800);
        int x = 80;
        for (int i = 0; i < 4; i++) {
            if(i == actualChoice){
                graphics2D.setColor(new Color(5, 119, 159));
            }else{
                graphics2D.setColor(new Color(119, 78, 0));
            }
            graphics2D.fillRect(x+160*i, 150, 150, 185);
            graphics2D.setColor(new Color(33, 30, 39));
            graphics2D.fillRect(x+5+160*i, 155, 140, 175);
            graphics2D.drawImage(heroesSprites.getSprite(0, i), x+5+160*i, 155, 140, 175, null);
            if(i == actualChoice){
                blue.drawStringOnCenter(FontSize.SMALL_FONT, heroesClassNames[i], i*160+80, 355, 150);
            }else{
                classic.drawStringOnCenter(FontSize.SMALL_FONT, heroesClassNames[i], i*160+80, 355, 150);
            }
        }
        classic.drawStringOnCenter(FontSize.BIG_FONT, "Start journey", 0, 600, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Press enter", 0, 650, 800);
    }

    public void onKeyClicked(Integer keyCode) {
        if(keyCode.equals(KeyEvent.VK_RIGHT) && actualChoice < 3){
            actualChoice++;
        }
        if(keyCode.equals(KeyEvent.VK_LEFT) && actualChoice > 0){
            actualChoice--;
        }
        if(keyCode.equals(KeyEvent.VK_ENTER)){
            observer.onCharacterChoose(actualChoice);
        }
    }
}