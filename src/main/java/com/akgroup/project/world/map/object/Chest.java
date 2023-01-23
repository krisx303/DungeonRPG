package com.akgroup.project.world.map.object;

import com.akgroup.project.graphics.Sprite;
import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.IInventoryObject;
import com.akgroup.project.world.inventory.key.Key;
import com.akgroup.project.world.inventory.mixtures.Potion;
import com.akgroup.project.world.inventory.weapon.BasicWeapon;
import com.akgroup.project.world.map.Hero;

public class Chest implements IMapObject {
    private IInventoryObject item;
    private int money;
    private int lvl;

    private boolean isLocked;

    public Chest(int lvl) {
        this.lvl = lvl;
        this.money = 0;
        this.item = randObject();
        this.isLocked = true;
    }

//    TODO level wyświetlić, skrzynke wyświetlić, skrzynke otworzyć

    private IInventoryObject randObject() {
        if (NumberGenerator.generateNextInt(0, 100) <= 45) {
            return Potion.HEALTH;
        } else if (NumberGenerator.generateNextInt(0, 100) <= 45) {
            money = NumberGenerator.generateMoney(lvl);
            return null;
        } else if (NumberGenerator.generateNextInt(0, 100) <= 30) {
            return new Key();
        } else if (NumberGenerator.generateNextInt(0, 6) <= 3) {
            return BasicWeapon.DAGGER;
        } else if (NumberGenerator.generateNextInt(0, 6) <= 3) {
            return BasicWeapon.KNIFE;
        } else {
            return BasicWeapon.STICK;
        }
    }


    public String getTypeOfValue() {
        if (money > 0) {
            return "int";
        }
        return "item";
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlockChest(Hero hero, int positionOfKeyInEq) {
        hero.getInventory().removeItemFromIndex(positionOfKeyInEq);
        isLocked = false;
    }

    public void getItemFromChest(Hero hero) {
        if (money > 0) {
            hero.setMoney(hero.getMoney() + money);
            money = 0;
        } else {
            if (!hero.getInventory().isInventoryFull()) {
                hero.getInventory().addItemToInventory(item);
                item = null;
            }
        }
    }

    public IInventoryObject getItem() {
        return item;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public void onInteraction() {

    }

    @Override
    public Sprite getSprite() {
        return Sprite.CHEST;
    }
}
