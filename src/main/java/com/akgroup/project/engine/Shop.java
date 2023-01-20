package com.akgroup.project.engine;

import com.akgroup.project.util.NumberGenerator;
import com.akgroup.project.world.inventory.IInventoryObject;
import com.akgroup.project.world.inventory.mixtures.Potion;
import com.akgroup.project.world.inventory.weapon.BasicWeapon;

public class Shop {
    private final IInventoryObject[] itemsToBuy;
    private final int[] prizes;

    private final int level;

    public Shop(int level) {
        this.prizes = new int[3];
        this.itemsToBuy = new IInventoryObject[3];
        this.level = level;
        this.randItems();
    }

    //    after creating new Shop you have to run shop.randItems(lvl)  !
    public void randItems() {
        for (int i = 0; i < 3; i++) {
            if (NumberGenerator.generateNextInt(0, 100) <= 70) {
                itemsToBuy[i] = Potion.HEALTH;
                prizes[i] = level * 5 + 20;
            }
            else if (NumberGenerator.generateNextInt(0, 6) <= 3) {
                itemsToBuy[i] = BasicWeapon.DAGGER;
                prizes[i] = level * 15 + 30;
            }
            else if (NumberGenerator.generateNextInt(0, 6) <= 3) {
                itemsToBuy[i] = BasicWeapon.KNIFE;
                prizes[i] = level * 15 + 35;
            }
            else{
                itemsToBuy[i] = BasicWeapon.STICK;
                prizes[i] = level * 15 + 45;
            }

        }
    }

    public IInventoryObject[] getItemsToBuy() {
        return itemsToBuy;
    }

    public int[] getPrizes() {
        return prizes;
    }

    public IInventoryObject getItemFromPosition(int position) {
        if (position < 0 || position > 2) {
            return null;
        }
        return itemsToBuy[position];
    }

    public int getValueOfiItemFromPosition(int position) {
        return prizes[position];
    }

    public void removeItemFromPosition(int position) {
        if (position < 0 || position > 2) {
            return;
        }
        itemsToBuy[position] = null;
    }
}
