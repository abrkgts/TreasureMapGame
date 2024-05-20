package com.example.TreasureMap;

import com.example.TreasureMap.Items.Adventurer.Adventurer;
import com.example.TreasureMap.Items.MapItem;
import com.example.TreasureMap.Items.Mountain;
import com.example.TreasureMap.Items.Treasure;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapBuilder {
    public Map buildMap(List<String> lines) {
        String[] dimensions = lines.get(0).split(" - ");
        int width = Integer.parseInt(dimensions[1]);
        int height = Integer.parseInt(dimensions[2]);

        List<MapItem> items = lines.stream()
                .skip(1)
                .map(this::parseLine)
                .collect(Collectors.toList());

        return new Map(width, height, items);
    }

    private MapItem parseLine(String line) {
        String[] parts = line.split(" - ");
        char type = parts[0].charAt(0);
        int horizontal, vertical;
        switch (type) {
            case 'M':
                horizontal = Integer.parseInt(parts[1]);
                vertical = Integer.parseInt(parts[2]);
                return new Mountain(new int[]{horizontal, vertical});
            case 'T':
                horizontal = Integer.parseInt(parts[1]);
                vertical = Integer.parseInt(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                return new Treasure(new int[]{horizontal, vertical}, quantity);
            case 'A':
                String name = parts[1];
                horizontal = Integer.parseInt(parts[2]);
                vertical = Integer.parseInt(parts[3]);
                char orientation = parts[4].charAt(0);
                String path = parts[5];
                return new Adventurer(name, new int[]{horizontal, vertical}, orientation, path);
            default:
                return null;
        }
    }
}
