package com.akgroup.project.graphics;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;

    private int currentFrame;

    private int delay;

    private int counter;

    public Animation(BufferedImage[] frames){
        this(frames, 2);
    }

    public Animation(BufferedImage[] frames, int delay){
        this.frames = frames;
        this.delay = delay;
        this.currentFrame = 0;
        this.counter = 0;
    }


    public BufferedImage getTexture() {
        if(delay == counter){
            counter = 0;
            currentFrame = (currentFrame+1) % frames.length;
        }
        counter++;
        return frames[currentFrame];
    }
}
