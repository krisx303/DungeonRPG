package com.akgroup.project.world.inventory;

import com.akgroup.project.world.inventory.weapon.basic.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Knife knife;
    private Inventory inventory;

    @BeforeEach
    void initialize() {
        knife = new Knife();
        inventory = new Inventory();
    }

    @Test
    void getItems() {
        inventory.addItemToInventory(knife);
        assertTrue(inventory.containsItem(knife));
    }

    @Test
    void getItemFromPosition() {
        inventory.addItemToInventory(knife);
        assertEquals(inventory.getItemFromPosition(0), knife);
    }

    @Test
    void isInventoryFull() {
        assertFalse(inventory.isInventoryFull());
        for (int i = 0; i < inventory.getCapacity(); i++) {
            inventory.addItemToInventory(knife);
        }
        assertTrue(inventory.isInventoryFull());
    }

    @Test
    void addItemToInventory() {
        for (int i = 0; i < inventory.getCapacity(); i++) {
            assertTrue(inventory.addItemToInventory(knife));
        }
        assertFalse(inventory.addItemToInventory(knife));
    }

    @Test
    void removeItem() {
        assertTrue(inventory.addItemToInventory(knife));
        inventory.removeItem(knife);
        assertFalse(inventory.containsItem(knife));
        assertEquals(0, inventory.getHowManyItems());
    }

    @Test
    void removeItemFromIndex() {
        assertTrue(inventory.addItemToInventory(knife));
        inventory.removeItemFromIndex(0);
        assertEquals(0, inventory.getHowManyItems());
    }
}