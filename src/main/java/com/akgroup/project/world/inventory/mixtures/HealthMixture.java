package com.akgroup.project.world.inventory.mixtures;

import com.akgroup.project.world.inventory.IInventoryObject;

public class HealthMixture implements IInventoryObject {
    //    how much hp mixture restores
    private final int strength;

    public HealthMixture() {
        this.strength = 50;
    }

    public int useMixture() {
        return strength;
    }

    @Override
    public String getName() {
        return "Heal mixture";
    }
}
