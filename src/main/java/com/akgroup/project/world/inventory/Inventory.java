package com.akgroup.project.world.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<IInventoryObject> items;
    private final int CAPACITY;

    public Inventory() {
        this.items = new ArrayList<>();
        this.CAPACITY = 10;
    }

    public List<IInventoryObject> getItems() {
        return items;
    }

    public IInventoryObject getItemFromPosition(int i) {
        return items.get(i);
    }

    public boolean isInventoryFull() {
        return items.size() == CAPACITY;
    }

    public boolean addItemToInventory(IInventoryObject item) {
        if (isInventoryFull()) {
            return false;
        }
        items.add(item);
        return true;
    }

    public void removeItem(IInventoryObject item) {
        items.remove(item);
    }

    public void removeItemFromIndex(int index) {
        items.remove(index);
    }


}
