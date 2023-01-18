package com.akgroup.project.world.exp;

public class ExpClass {
    private int exp;
    private final int[] LVL_RANGES;
    private int currHeroLvl;
    private int currLvlIndex;

    public ExpClass() {
        this.exp = 0;
        this.LVL_RANGES = new int[]{10, 50, 150, 250, 500, 100};
        this.currHeroLvl = 1;
        this.currLvlIndex = 0;
    }

    public int getCurrHeroLvl() {
        return currHeroLvl;
    }

    public void increaseExp(int newExp) {
        this.exp += exp;
        increaseLvlIfNecessary();
    }

    private void increaseLvlIfNecessary() {
        while (exp > LVL_RANGES[currHeroLvl]) {
            increaseLvl();
            increaseLvlIndex();
        }
    }

    private void increaseLvl() {
        this.currHeroLvl++;
    }

    private void increaseLvlIndex() {
        this.currLvlIndex++;
    }


}
