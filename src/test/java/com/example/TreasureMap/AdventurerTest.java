package com.example.TreasureMap;

import com.example.TreasureMap.Items.Adventurer.Adventurer;
import com.example.TreasureMap.Items.Adventurer.AdventurerMovementService;
import com.example.TreasureMap.Items.Adventurer.AdventurerTreasureService;
import com.example.TreasureMap.Items.Treasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AdventurerTest {

    private Adventurer adventurer;
    private Map map;
    private AdventurerMovementService adventurerMovementService;
    private AdventurerTreasureService adventurerTreasureService;

    @BeforeEach
    void setUp() {
        adventurer = new Adventurer("Lara", new int[]{1, 1}, 'S', "AADADAGGA");
        map = new Map(3, 4, List.of(adventurer));
        adventurerMovementService = new AdventurerMovementService();
        adventurerTreasureService = new AdventurerTreasureService();
    }

    @Test
    void shouldMove() {
        int[] newPosition = adventurerMovementService.move(adventurer.getPosition(), adventurer.getOrientation(), map.getWidth(), map.getHeight());
        Assertions.assertArrayEquals(new int[]{1, 2}, newPosition);
    }

    @Test
    void shouldChangeOrientation() {
        adventurer.setOrientation(adventurerMovementService.changeOrientation(adventurer.getOrientation(), 'G'));
        Assertions.assertEquals('E', adventurer.getOrientation());
    }

    @Test
    void shouldCollectTreasure() {
        Treasure newTreasure = new Treasure(new int[]{1, 2}, 1);
        map.setItems(List.of(newTreasure));
        adventurer.setPosition(new int[]{1, 2});
        adventurerTreasureService.collectTreasure(adventurer, newTreasure);
        Assertions.assertEquals(1, adventurer.getTreasures());
    }
}
