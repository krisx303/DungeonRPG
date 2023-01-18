package com.akgroup.project.engine;

import com.akgroup.project.graphics.Animation;
import com.akgroup.project.graphics.SpriteLoader;
import com.akgroup.project.graphics.SpriteSheet;
import com.akgroup.project.world.map.WorldMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private Animation currentAnimation;

    private final int startX = 380;
    private final int startY = 380;

    private final int width = 40;
    private final int height = 40;

    private final WorldMap map;

    private final PlayerCollision collision;

    private final WorldPosition worldPosition;
    private SpriteSheet spriteSheet;

    public Player(WorldPosition worldPosition, WorldMap worldMap) {
        this.worldPosition = worldPosition;
        this.map = worldMap;
        this.collision = new PlayerCollision(worldPosition, worldMap, width, height);
    }


    public void update(){
        int playerX = worldPosition.getPositionX() + startX;
        int playerY = worldPosition.getPositionY() + startY;
        collision.update(playerX, playerY);
    }

    public void render(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(71, 15, 183));
        graphics2D.fillRect(380, 380, 40, 40);
        graphics2D.drawImage(currentAnimation.getTexture(), startX-25, startY-30, width*2, height*2, null);
    }

    public void loadTextures() throws IOException {
        BufferedImage bufferedImage = SpriteLoader.loadSprite("entity/player.png");
        spriteSheet = new SpriteSheet(bufferedImage, 8, 4, 32, 32);
        currentAnimation = spriteSheet.createAnimation(0);
    }
}
