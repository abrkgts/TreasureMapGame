package com.example.TreasureMap;

import com.example.TreasureMap.Items.Adventurer.Adventurer;
import com.example.TreasureMap.Items.Adventurer.AdventurerMovementService;
import com.example.TreasureMap.Items.Adventurer.AdventurerTreasureService;
import com.example.TreasureMap.Items.MapItem;
import com.example.TreasureMap.Items.Treasure;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TreasureMapGame {
    private Map map;
    private final AdventurerMovementService adventurerMovementService;
    private final AdventurerTreasureService adventurerTreasureService;


    public TreasureMapGame(Map map, AdventurerMovementService adventurerMovementService, AdventurerTreasureService adventurerTreasureService) {
        this.map = map;
        this.adventurerMovementService = adventurerMovementService;
        this.adventurerTreasureService = adventurerTreasureService;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void play() {
        List<Adventurer> adventurers = map.getAdventurers();
        for (Adventurer adventurer : adventurers) {
            String path = adventurer.getPath();
            for (char step : path.toCharArray()) {
                if (step == 'A') {
                    int[] newPosition = adventurerMovementService.move(adventurer.getPosition(), adventurer.getOrientation(), map.getWidth(), map.getHeight());
                    handleMove(adventurer, newPosition);
                } else {
                    adventurer.setOrientation(adventurerMovementService.changeOrientation(adventurer.getOrientation(), step));
                }
            }
        }
    }

    private void handleMove(Adventurer adventurer, int[] newPosition) {
        if (!Arrays.equals(adventurer.getPosition(), newPosition)) {
            if (map.isPositionOccupied(newPosition)) {
                Optional<MapItem> itemOptional = map.getItemFromPosition(newPosition);
                if (itemOptional.isPresent()) {
                    MapItem item = itemOptional.get();
                    if (item instanceof Treasure) {
                        adventurerTreasureService.collectTreasure(adventurer, (Treasure) item);
                        adventurer.setPosition(newPosition);
                    }
                }
            }
            else adventurer.setPosition(newPosition);
        }
    }
}
