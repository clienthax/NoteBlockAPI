package com.xxmicloxx.NoteBlockAPI;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Song {

    private Map<Integer, Layer> layerMap = new HashMap<>();
    private short songHeight;
    private short length;
    private String title;
    private File path;
    private String author;
    private String description;
    private float speed;
    private float delay;

    public Song(Song other) {
        this.speed = other.getSpeed();
        delay = 20 / speed;
        this.layerMap = other.getLayerMap();
        this.songHeight = other.getSongHeight();
        this.length = other.getLength();
        this.title = other.getTitle();
        this.author = other.getAuthor();
        this.description = other.getDescription();
        this.path = other.getPath();
    }

    public Song(float speed, Map<Integer, Layer> layerMap,
                short songHeight, final short length, String title, String author,
                String description, File path) {
        this.speed = speed;
        delay = 20 / speed;
        this.layerMap = layerMap;
        this.songHeight = songHeight;
        this.length = length;
        this.title = title;
        this.author = author;
        this.description = description;
        this.path = path;
    }

    public Map<Integer, Layer> getLayerMap() {
        return layerMap;
    }

    public short getSongHeight() {
        return songHeight;
    }

    public short getLength() {
        return length;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public File getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDelay() {
        return delay;
    }
}
