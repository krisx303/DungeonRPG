package com.akgroup.project.engine;

import com.akgroup.project.util.EntityDrop;
import com.akgroup.project.world.characters.enemies.AbstractEnemyClass;
import com.akgroup.project.world.inventory.IInventoryObject;

public interface IGameObserver {
    void onCharacterChoose(int classID);

    void onInteractionExit();

    void enterRoom(int roomID, AbstractEnemyClass enemy);

    void onGameOver();

    void onEnemyDefeated(EntityDrop drop);
}
