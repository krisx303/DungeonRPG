package com.akgroup.project.world.map;

import com.akgroup.project.Main;
import com.akgroup.project.graphics.SpriteLoader;
import com.akgroup.project.util.Vector2d;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;


public class MapLoader {

    public static MapLevel loadMapLevel(int level) throws IOException, ParserConfigurationException, SAXException {
        MapLevel mapLevel = new MapLevel();
        BufferedImage background = SpriteLoader.loadSprite("dungeon level %d.png".formatted(level));
        mapLevel.setBackground(background);
        loadTileMap(mapLevel, level);
        return mapLevel;
    }

    private static void loadTileMap(MapLevel mapLevel, int level) throws IOException, ParserConfigurationException, SAXException {
        String path = "dungeon level %d.tmx".formatted(level);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(Main.class.getResourceAsStream(path));
        doc.getDocumentElement().normalize();
        NodeList list = doc.getElementsByTagName("layer");
        Node item = list.item(0);
        String data = ((Element)item).getElementsByTagName("data").item(0).getTextContent();
        String[] rows = data.split("\n");
        for (int row = 1; row < rows.length; row++) {
            String[] cells = rows[row].split(",");
            for (int col = 0; col < cells.length; col++) {
                if(Integer.parseInt(cells[col]) == 172){
                    mapLevel.addBarrier(new Vector2d(col, row-1));
                }
            }
        }
    }
}
