package com.akgroup.project.engine;

import com.akgroup.project.gui.GameWindow;
import com.akgroup.project.gui.Panel;

import java.awt.*;
import java.io.IOException;

public class Engine {

    private Game game;
    public final Dimension dimension;
    private Panel panel;

    public Engine(Dimension dimension) {
        this.dimension = dimension;
    }

    public void init() {
        panel = new Panel(dimension);
        GameWindow window = new GameWindow(panel);

        game = new Game(dimension, panel.getGraphics2D());
        panel.addKeyListener(game);
        try {
            game.initGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is responsible for keeping game working on 60 FPS and execute all updates.
     */
    public void infinityLoop() {
        final double GAME_FPS = 60.0;
        final double TBU = 1000000000 / GAME_FPS; //time before update
        final int MUBR = 5; //Must Update before render
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;
        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS; //Total time before render

        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        while (true) {
            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                game.update();
                //game.input();
                lastUpdateTime += TBU;
                updateCount++;
            }
            if (now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }
            //game.input();
            game.render();
            panel.draw();
            lastRenderTime = now;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                lastSecondTime = thisSecond;
            }
            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
                //Thread.yield();
                waitANanosecond();
                now = System.nanoTime();
            }
        }
    }

    private static void waitANanosecond() {
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            System.out.println("ERROR: yielding thread");
        }
    }


}
