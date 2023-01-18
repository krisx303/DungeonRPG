package com.akgroup.project.gui;

import javax.swing.*;

public class GameWindow extends JFrame {

    private static final String TITLE = "Dungeon RPG";

    public GameWindow(JPanel panel) {
        setContentPane(panel);
        setDefaultSettings();
    }

    private void setDefaultSettings() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        invalidate();
        validate();
        repaint();
        pack();
        dispose();
        setResizable(false);
        setVisible(true);
    }
}
