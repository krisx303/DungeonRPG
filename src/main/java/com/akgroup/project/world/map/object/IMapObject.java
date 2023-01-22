package com.akgroup.project.world.map.object;

import com.akgroup.project.graphics.Sprite;

public interface IMapObject {
    void onInteraction();

    Sprite getSprite();
}