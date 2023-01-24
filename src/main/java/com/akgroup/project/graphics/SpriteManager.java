package com.akgroup.project.graphics;

import com.akgroup.project.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class SpriteManager {

    private static final HashMap<Sprite, BufferedImage> sprites = new HashMap<>();


    public static void loadSprites() throws IOException {
        loadSprite(Sprite.HEROES, "entity/heroes2.png");
        loadSprite(Sprite.CLASSIC_FONT, "font/font.png");
        loadSprite(Sprite.BLUE_FONT, "font/font_blue.png");
        loadSprite(Sprite.HEAL_POTION, "item/potion.png");
        loadSprite(Sprite.DAGGER, "item/dagger.png");
        loadSprite(Sprite.KNIFE, "item/knife.png");
        loadSprite(Sprite.STICK, "item/stick.png");
        loadSprite(Sprite.SHOP, "object/shop.png");
        loadSprite(Sprite.CHEST, "object/chest.png");
        loadSprite(Sprite.FIGHT_BACKGROUND, "fight-background.png");
        loadSprite(Sprite.ENEMY, "entity/enemy.png");
        loadSprite(Sprite.BOSS, "entity/boss.png");
        loadSprite(Sprite.KEY, "item/key.png");
    }

    private static void loadSprite(Sprite sprite, String filename) throws IOException {
        BufferedImage image = loadBufferedImage(filename);
        sprites.put(sprite, image);
    }

    public static BufferedImage loadBufferedImage(String filename) throws IOException {
        return ImageIO.read(Main.class.getResourceAsStream(filename));
    }

    public static BufferedImage getSprite(Sprite sprite) {
        return sprites.get(sprite);
    }
}
