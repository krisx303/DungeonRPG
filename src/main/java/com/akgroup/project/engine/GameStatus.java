package com.akgroup.project.engine;

public enum GameStatus {
    CHARACTER_CHOOSING,
    IN_GAME,
    FIGHT_GAME,
    INVENTORY,
    OPENED_DIALOG;

    public boolean isCharacterChoosingMenu() {
        return this == CHARACTER_CHOOSING;
    }
}
