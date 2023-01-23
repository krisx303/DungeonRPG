package com.akgroup.project.world.exp;

public class ExpClass {
    private int exp;
    private final int[] LVL_RANGES;
    private int currHeroLvl;
    private int currLvlIndex;

    private int lvlPointsToAdd;

    public ExpClass() {
        this.exp = 0;
        this.LVL_RANGES = new int[]{10, 50, 150, 250, 500, 100};
        this.currHeroLvl = 1;
        this.currLvlIndex = 0;
        this.lvlPointsToAdd = 0;
    }

    public int getCurrHeroLvl() {
        return currHeroLvl;
    }

    public int increaseExp(int newExp) {
        this.exp += newExp;
        return increaseLvlIfNecessary();
    }

    private int increaseLvlIfNecessary() {
        int counter = 0;
        while (exp > LVL_RANGES[currHeroLvl]) {
            increaseLvl();
            increaseLvlIndex();
            counter++;
            lvlPointsToAdd++;
        }
        return counter;
    }

    public int getLvlPointsToAdd() {
        return lvlPointsToAdd;
    }

    public void usePointLvl() {
        lvlPointsToAdd -= 1;
    }

    private void increaseLvl() {
        this.currHeroLvl++;
    }

    private void increaseLvlIndex() {
        this.currLvlIndex++;
    }


}
