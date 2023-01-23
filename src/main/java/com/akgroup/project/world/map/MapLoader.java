package com.akgroup.project.world.map;

import com.akgroup.project.Main;
import com.akgroup.project.graphics.SpriteManager;
import com.akgroup.project.util.Vector2d;
import com.akgroup.project.world.map.object.Chest;
import com.akgroup.project.world.map.object.Stairs;
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
        BufferedImage background = SpriteManager.loadBufferedImage("map/dungeon level %d.png".formatted(level));
        mapLevel.setBackground(background);
        loadTileMap(mapLevel, level);
        return mapLevel;
    }

    private static void loadTileMap(MapLevel mapLevel, int level) throws IOException, ParserConfigurationException, SAXException {
        String path = "map/dungeon level %d.tmx".formatted(level);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(Main.class.getResourceAsStream(path));
        doc.getDocumentElement().normalize();
        NodeList layers = doc.getElementsByTagName("layer");
        loadBarriers(mapLevel, (Element) layers.item(0));
        loadRooms(mapLevel, (Element) layers.item(4), (Element) layers.item(3), level);
    }


    private static final int MIN_ROOM_DOOR = 365;
    private static final int MIN_ROOM = 397;
    private static final int MAX_ROOM = MIN_ROOM + 23;

    private static final int DOOR_BARRIER = 170;

    private static final int SHOP_BARRIER = 169;

    private static final int SHOP = 473;

    private static final int ENEMY = 582;

    private static final int CHEST = 254;

    private static final int STAIRS_UP = 324;

    private static final int STAIRS_DOWN = 325;

    private static final int START_POS_WHEN_GOING_DOWN = 331;
    private static final int START_POS_WHEN_GOING_UP = 330;

    private static final int BOSS = 577;

    private static void loadRooms(MapLevel mapLevel, Element informationLayer, Element objectLayer, int level) {
        String information = informationLayer.getElementsByTagName("data").item(0).getTextContent();
        String[] infoRows = information.split("\n");
        String object = objectLayer.getElementsByTagName("data").item(0).getTextContent();
        String[] objectRows = object.split("\n");

        for (int row = 1; row < infoRows.length; row++) {
            String[] infoCells = infoRows[row].split(",");
            String[] objectCells = objectRows[row].split(",");
            for (int col = 0; col < infoCells.length; col++) {
                int infoVal = Integer.parseInt(infoCells[col]);
                int objectVal = Integer.parseInt(objectCells[col]);
                loadMapInformation(mapLevel, infoVal, objectVal, level, new Vector2d(col, row-1));
            }
        }
    }

    private static void loadMapInformation(MapLevel mapLevel, int infoVal, int objectVal, int level, Vector2d pos) {
        if(infoVal > MAX_ROOM){
            if(infoVal == SHOP){
                mapLevel.addShopAtPosition(pos);
            }
        }else if(infoVal >= MIN_ROOM){
            int roomID = infoVal - MIN_ROOM;
            if(objectVal == DOOR_BARRIER){
                mapLevel.addRoomBarrier(roomID, pos);
            }
            else if(objectVal == ENEMY){
                mapLevel.addWeakEnemyToRoom(roomID, level);
            }
            else if(objectVal == BOSS){
                mapLevel.addBoosToRoom(roomID, level);
            }
            else if(objectVal == CHEST){
                Chest chest = new Chest();
                mapLevel.addChestAtPosition(roomID, pos, chest);
            }
        }else if(infoVal >= MIN_ROOM_DOOR){
            mapLevel.addRoomDoor(pos, infoVal - MIN_ROOM_DOOR);
        }else {
            switch (infoVal) {
                case STAIRS_UP -> mapLevel.addStairs(pos, new Stairs(true));
                case STAIRS_DOWN -> mapLevel.addStairs(pos, new Stairs(false));
                case START_POS_WHEN_GOING_DOWN -> mapLevel.addStartPosDown(pos);
                case START_POS_WHEN_GOING_UP -> mapLevel.addStartPosUp(pos);
            }
        }

        if(objectVal == SHOP_BARRIER){
            mapLevel.addBarrier(pos);
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
