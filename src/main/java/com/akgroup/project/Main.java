package com.akgroup.project;

import com.akgroup.project.engine.Engine;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension dimension = new Dimension(800, 800);
        Engine engine = new Engine(dimension);
        engine.init();
        engine.infinityLoop();
    }
}