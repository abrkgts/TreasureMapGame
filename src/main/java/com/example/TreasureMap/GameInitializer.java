package com.example.TreasureMap;

import com.example.TreasureMap.Items.Adventurer.AdventurerMovementService;
import com.example.TreasureMap.Items.Adventurer.AdventurerTreasureService;
import org.springframework.stereotype.Component;

@Component
public class GameInitializer {

    public TreasureMapGame initializeGame(Map map) {
        AdventurerMovementService movementService = new AdventurerMovementService();
        AdventurerTreasureService treasureService = new AdventurerTreasureService();
        return new TreasureMapGame(map, movementService, treasureService);
    }
}
