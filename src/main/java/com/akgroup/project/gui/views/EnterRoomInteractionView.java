package com.akgroup.project.gui.views;

import com.akgroup.project.engine.IGameObserver;
import com.akgroup.project.graphics.Font;
import com.akgroup.project.graphics.FontManager;
import com.akgroup.project.graphics.FontSize;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EnterRoomInteractionView extends InteractionView {
    private Font classic, blue;
    private int actualChoice;

    private final int roomID;

    private final AbstractEnemyClass enemy;

    private final boolean chest;

    private static final String[] options = new String[]{"Enter the room", "Go away"};

    public EnterRoomInteractionView(Graphics2D graphics2D, IGameObserver observer, int roomID, AbstractEnemyClass enemyInRoom, boolean chestInRoom) {
        super(graphics2D, observer);
        this.roomID = roomID;
        this.enemy = enemyInRoom;
        this.chest = chestInRoom;
    }

    @Override
    public void render() {
        int x = 70;
        graphics2D.setColor(new Color(119, 78, 0));
        graphics2D.fillRect(x, 150, 660, 355);
        graphics2D.setColor(new Color(33, 30, 39));
        graphics2D.fillRect(x + 5, 155, 650, 345);
        classic.drawStringOnCenter(FontSize.BIG_FONT, "Do you want enter?", 0, 160, 800);
        classic.drawString(FontSize.SMALL_FONT, "In this room you will find:", 80, 240);
        int y = 280;
        if(enemy != null){
            classic.drawString(FontSize.SMALL_FONT, "* Enemy", 100, y);
            y += 40;
        }
        if(chest){
            classic.drawString(FontSize.SMALL_FONT, "* Chest", 100, y);
        }
        for (int i = 0; i < options.length; i++) {
            if(i == actualChoice){
                blue.drawStringOnCenter(FontSize.BIG_FONT, options[i], 0, 380+60*i, 800);
            }else{
                classic.drawStringOnCenter(FontSize.BIG_FONT, options[i], 0, 380+60*i, 800);
            }
        }
    }

    @Override
    public void onKeyClicked(Integer keyCode) {
        if(keyCode.equals(KeyEvent.VK_DOWN) && actualChoice < 1){
            actualChoice++;
        }else if(keyCode.equals(KeyEvent.VK_UP) && actualChoice > 0){
            actualChoice--;
        }else if(keyCode.equals(KeyEvent.VK_ENTER)){
            if(actualChoice == 0){
                observer.enterRoom(roomID, enemy);
            }else{
                observer.onInteractionExit();
            }
        }
    }

    @Override
    public void initView() {
        this.classic = FontManager.getManager().getClassic();
        this.blue = FontManager.getManager().getBlue();
    }
}
