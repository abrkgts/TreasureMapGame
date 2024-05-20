package com.example.TreasureMap.Items.Adventurer;

import com.example.TreasureMap.Items.Treasure;
import org.springframework.stereotype.Service;

@Service
public class AdventurerTreasureService {
    public void collectTreasure(Adventurer adventurer, Treasure treasure) {
        if (treasure.getQuantity() > 0) {
            adventurer.addTreasure();
            treasure.removeOne();
        }
    }
}
