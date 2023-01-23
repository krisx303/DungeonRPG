package com.akgroup.project.gui.views;

import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.*;
import com.akgroup.project.graphics.Font;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CharacterInteractionView extends InteractionView{

    private SpriteSheet heroesSprites;

    private Font classic;

    private int actualChoice = 0;

    private final String[] heroesClassNames = new String[]{"Ninja", "Fighter", "Mage", "Heavy"};
    private final String[] heroesCritStats = new String[]{"10", "60", "1", "5"};
    private final String[] heroesDodgeStats = new String[]{"70", "3", "35", "2"};
    private final String[] heroesHealStats = new String[]{"1", "1", "70", "2"};
    private final String[] heroesArmorStats = new String[]{"1", "10", "5", "25"};
    private final String[] heroesWeapons = new String[]{"dagger", "stick", "random", "knife"};

    public CharacterInteractionView(Graphics2D graphics2D, IGameObserver observer) {
        super(graphics2D, observer);
    }

    @Override
    public void render() {
        classic.drawStringOnCenter(FontSize.BIG_FONT, "Select class", 0, 20, 800);
        int x = 80;
        for (int i = 0; i < 4; i++) {
            if (i == actualChoice) {
                graphics2D.setColor(new Color(5, 119, 159));
            } else {
                graphics2D.setColor(new Color(119, 78, 0));
            }
            graphics2D.fillRect(x + 160 * i, 150, 150, 185);
            graphics2D.setColor(new Color(33, 30, 39));
            graphics2D.fillRect(x + 5 + 160 * i, 155, 140, 175);
            graphics2D.drawImage(heroesSprites.getSprite(1, i), x + 5 + 160 * i, 155, 140, 175, null);
            classic.drawStringOnCenter(FontSize.SMALL_FONT, heroesClassNames[i], i * 160 + 80, 355, 150);
        }
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "crit:", 2, 380, 120);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "dodge:", 2, 410, 120);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "heal:", 2, 440, 120);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "armor:", 2, 470, 120);
//        classic.drawStringOnCenter(FontSize.SMALL_FONT, "weapon:", 2, 530, 138);
        for (int i = 0; i < 4; i++) {
            classic.drawStringOnCenter(FontSize.SMALL_FONT, heroesCritStats[i], i * 160 + 80, 380, 150);
            classic.drawStringOnCenter(FontSize.SMALL_FONT, heroesDodgeStats[i], i * 160 + 80, 410, 150);
            classic.drawStringOnCenter(FontSize.SMALL_FONT, heroesHealStats[i], i * 160 + 80, 440, 150);
            classic.drawStringOnCenter(FontSize.SMALL_FONT, heroesArmorStats[i], i * 160 + 80, 470, 150);
            classic.drawStringOnCenter(FontSize.SMALL_FONT, heroesWeapons[i], i * 160 + 80, 530, 150);
        }
        classic.drawStringOnCenter(FontSize.BIG_FONT, "Start journey", 0, 600, 800);
        classic.drawStringOnCenter(FontSize.SMALL_FONT, "Press enter", 0, 650, 800);
    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if (keyCode.equals(KeyEvent.VK_RIGHT) && actualChoice < 3) {
            actualChoice++;
        }
        if (keyCode.equals(KeyEvent.VK_LEFT) && actualChoice > 0) {
            actualChoice--;
        }
        if (keyCode.equals(KeyEvent.VK_ENTER)) {
            observer.onCharacterChoose(actualChoice);
        }
    }

    @Override
    public void initView() {
        BufferedImage heroes = SpriteManager.getSprite(Sprite.HEROES);
        heroesSprites = new SpriteSheet(heroes, 4, 4, 24, 30);
        this.classic = FontManager.getManager().getClassic();
    }
}
