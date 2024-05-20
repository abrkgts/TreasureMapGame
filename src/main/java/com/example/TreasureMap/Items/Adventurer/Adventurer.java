package com.example.TreasureMap.Items.Adventurer;

import com.example.TreasureMap.Items.MapItem;

public class Adventurer implements MapItem {
    private final String name;
    private int[] position;
    private char orientation;
    private String path;
    private int treasures;

    public Adventurer(String name, int[] position, char orientation, String path) {
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTreasures() {
        return treasures;
    }

    public void addTreasure() {
        this.treasures++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adventurer that = (Adventurer) o;
        return java.util.Objects.equals(name, that.name) && java.util.Objects.deepEquals(position, that.position) && java.util.Objects.equals(orientation, that.orientation) && java.util.Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, java.util.Arrays.hashCode(position), orientation, path);
    }

    @Override
    public String toString() {
        return "Items.Adventurer.Items.Adventurer{" +
                "name='" + name + '\'' +
                ", position=" + java.util.Arrays.toString(position) +
                ", orientation='" + orientation + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
