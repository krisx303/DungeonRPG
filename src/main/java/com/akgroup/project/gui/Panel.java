package com.akgroup.project.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {
    private final int width;
    private final int height;
    private final BufferedImage image;
    private final Graphics2D graphics2D;
    public Panel(Dimension dimension){
        this.width = dimension.width;
        this.height = dimension.height;
        setPreferredSize(dimension);
        setFocusable(true);
        requestFocus();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) image.getGraphics();
    }

    public Graphics2D getGraphics2D(){
        return graphics2D;
    }

    public void draw(){
        getGraphics().drawImage(image, 0, 0, width, height, null);
    }
}