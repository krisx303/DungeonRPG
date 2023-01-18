package com.akgroup.project.engine;

public enum GameStatus {
    CHARACTER_CHOOSING,
    IN_GAME,
    FIGHT_GAME;

    public boolean isCharacterChoosingMenu() {
        return this == CHARACTER_CHOOSING;
    }
}
