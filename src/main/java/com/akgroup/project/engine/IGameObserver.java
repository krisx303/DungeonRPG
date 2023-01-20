package com.akgroup.project.engine;

public interface IGameObserver {
    void onCharacterChoose(int classID);

    void onInteractionExit();
}
