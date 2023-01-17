package com.akgroup.project.world.inventory;

public class Inventory {

    private final IInventoryObject[] items;
    private static final int CAPACITY = 10;
    private int howManyItems;

    public Inventory() {
        this.howManyItems = 0;
        this.items = new IInventoryObject[CAPACITY];
    }

    public IInventoryObject[] getItems() {
        return items;
    }

    public IInventoryObject getItemFromPosition(int i) {
        return items[i];
    }

    public boolean isInventoryFull() {
        return howManyItems == CAPACITY;
    }

    private int getNextEmptySlot(){
        for (int i = 0; i < CAPACITY; i++) {
            if(hasNoItemAtSlot(i)) {
                return i;
            }
        }
        return -1;
    }

    public boolean addItemToInventory(IInventoryObject item) {
        if (isInventoryFull()) {
            return false;
        }
        int slot = getNextEmptySlot();
        items[slot] = item;
        howManyItems++;
        return true;
    }

    public void removeItem(IInventoryObject item) {
        for (int i = 0; i < CAPACITY; i++) {
            if(hasItemAtIndex(i) && getItemFromPosition(i).equals(item)){
                removeItemFromIndex(i);
                break;
            }
        }
    }

    private boolean hasNoItemAtSlot(int i){
        return items[i] == null;
    }

    private boolean hasItemAtIndex(int i){
        return items[i] != null;
    }

    public void removeItemFromIndex(int index) {
        items[index] = null;
        howManyItems--;
    }


    public boolean containsItem(IInventoryObject item){
        for (int i = 0; i < CAPACITY; i++) {
            if(hasItemAtIndex(i) && getItemFromPosition(i).equals(item)){
                return true;
            }
        }
        return false;
    }

    public int getCapacity(){
        return CAPACITY;
    }

    public int getHowManyItems() {
        return howManyItems;
    }
}
