package com.akgroup.project.world.map;

public class MapLoadingException extends RuntimeException {
    public MapLoadingException(String message) {
        super("Could not load map: " + message);
    }
}
