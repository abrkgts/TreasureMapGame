package com.example.TreasureMap;

import com.example.TreasureMap.Items.Adventurer.Adventurer;
import com.example.TreasureMap.Items.MapItem;

public class Map {
    private final int width;
    private final int height;
    private java.util.List<MapItem> items;

    public Map(int width, int height, java.util.List<MapItem> items) {
        this.width = width;
        this.height = height;
        this.items = items;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public java.util.List<MapItem> getItems() {
        return items;
    }

    public void setItems(java.util.List<MapItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return width == map.width && height == map.height && java.util.Objects.equals(items, map.items);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(width, height, items);
    }

    public boolean isPositionOccupied(int[] position) {
        return this.items.stream().anyMatch(item -> item.getPosition()[0] == position[0] &&
                                item.getPosition()[1] == position[1]
        );
    }

    @Override
    public String toString() {
        return "Map{" +
                "width=" + width +
                ", height=" + height +
                ", items=" + items +
                '}';
    }

    public java.util.Optional<MapItem> getItemFromPosition(int[] position) {
        return this.items.stream().filter(item ->
                item.getPosition()[0] == position[0] &&
                        item.getPosition()[1] == position[1]).findFirst();
    }

    public java.util.List<Adventurer> getAdventurers() {
        return this.items.stream()
                .filter(item -> item instanceof Adventurer)
                .filter(adventurer ->
                        0 <= adventurer.getPosition()[0] &&
                                adventurer.getPosition()[0] < this.width &&
                                0 <= adventurer.getPosition()[1] &&
                                adventurer.getPosition()[1] < this.height
                )
                .map(adventurer -> (Adventurer) adventurer)
                .toList();
    }
}
