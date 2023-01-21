package com.akgroup.project.world.map;

import com.akgroup.project.Main;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.map.object.Chest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MapLoader {

    public static MapLevel loadMapLevel(int level) throws IOException, ParserConfigurationException, SAXException {
        MapLevel mapLevel = new MapLevel();
        BufferedImage background = SpriteManager.loadBufferedImage("dungeon level %d.png".formatted(level));
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
        NodeList layers = doc.getElementsByTagName("layer");
        loadBarriers(mapLevel, (Element) layers.item(0));
        loadRooms(mapLevel, (Element) layers.item(3), (Element) layers.item(2));
    }


    private static final int MIN_ROOM_DOOR = 365;
    private static final int MIN_ROOM = 397;

    private static final int DOOR_BARRIER = 170;

    private static final int ENEMY = 582;

    private static final int CHEST = 254;

    private static void loadRooms(MapLevel mapLevel, Element informationLayer, Element objectLayer) {
        String information = informationLayer.getElementsByTagName("data").item(0).getTextContent();
        String[] infoRows = information.split("\n");
        String object = objectLayer.getElementsByTagName("data").item(0).getTextContent();
        String[] objectRows = object.split("\n");

        for (int row = 1; row < infoRows.length; row++) {
            String[] infoCells = infoRows[row].split(",");
            String[] objectCells = objectRows[row].split(",");
            for (int col = 0; col < infoCells.length; col++) {
                int infoVal = Integer.parseInt(infoCells[col]);
                if(infoVal >= MIN_ROOM){
                    if(!objectCells[col].equals("0")){
                        int objectVal = Integer.parseInt(objectCells[col]);
                        if(objectVal == DOOR_BARRIER){
                            mapLevel.addRoomBarrier(infoVal - MIN_ROOM, new Vector2d(col, row-1));
                        }else if(objectVal == ENEMY){
                            mapLevel.addWeakEnemyToRoom(infoVal - MIN_ROOM);
                        }else if(objectVal == CHEST){
                            Chest chest = new Chest();
                            mapLevel.addChestAtPosition(infoVal - MIN_ROOM, new Vector2d(col, row-1), chest);
                            //mapLevel.addRoomObject(infoVal - MIN_ROOM, objectVal);
                        }
                    }
                }else if(infoVal >= MIN_ROOM_DOOR){
                    mapLevel.addRoomDoor(new Vector2d(col, row-1), infoVal - MIN_ROOM_DOOR);
                }
            }
        }
    }

    private static void loadBarriers(MapLevel mapLevel, Element layer){
        String data = layer.getElementsByTagName("data").item(0).getTextContent();
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
