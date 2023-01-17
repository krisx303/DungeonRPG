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
        assertTrue(inventory.getItems().contains(knife));
    }

    @Test
    void getItemFromPosition() {
        inventory.addItemToInventory(knife);
        assertEquals(inventory.getItemFromPosition(0), knife);
    }

    @Test
    void isInventoryFull() {
        assertFalse(inventory.isInventoryFull());
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        inventory.addItemToInventory(knife);
        assertTrue(inventory.isInventoryFull());
    }

    @Test
    void addItemToInventory() {
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertTrue(inventory.addItemToInventory(knife));
        assertFalse(inventory.addItemToInventory(knife));
    }

    @Test
    void removeItem() {
        assertTrue(inventory.addItemToInventory(knife));
        inventory.removeItem(knife);
        assertEquals(inventory.getItems().size(), 0);
    }

    @Test
    void removeItemFromIndex() {
        assertTrue(inventory.addItemToInventory(knife));
        inventory.removeItemFromIndex(0);
        assertEquals(inventory.getItems().size(), 0);
    }
}