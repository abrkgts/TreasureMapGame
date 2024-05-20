package com.example.TreasureMap;

import com.example.TreasureMap.Items.Adventurer.Adventurer;
import com.example.TreasureMap.Items.Adventurer.AdventurerMovementService;
import com.example.TreasureMap.Items.Adventurer.AdventurerTreasureService;
import com.example.TreasureMap.Items.MapItem;
import com.example.TreasureMap.Items.Mountain;
import com.example.TreasureMap.Items.Treasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TreasureMapGameTest {
    private List<MapItem> mapItems;
    private Map map;
    private TreasureMapGame treasureMapGame;
    private GameInitializer gameInitializer;

    @BeforeEach
    void setUp() {
        mapItems = new ArrayList<>();
        mapItems.add(new Mountain(new int[]{2, 2}));
        mapItems.add(new Treasure(new int[]{0, 2}, 1));
        mapItems.add(new Treasure(new int[]{1, 0}, 2));
        mapItems.add(new Adventurer("Lara", new int[]{1, 1}, 'S', "GAGAGAAGAA"));
        map = new Map(3, 4, mapItems);
        gameInitializer = new GameInitializer();
        treasureMapGame = gameInitializer.initializeGame(map);
    }

    @Test
    void shouldPickUpTreasureWithoutMountain() {
        treasureMapGame.play();
        int treasureQuantity = map.getAdventurers()
                .stream()
                .mapToInt(Adventurer::getTreasures)
                .sum();

        Assertions.assertEquals(2, treasureQuantity);
    }

    @Test
    void shouldPickUpTreasureWithMountain() {
        mapItems.clear();
        mapItems.add(new Mountain(new int[]{2, 1}));
        mapItems.add(new Treasure(new int[]{0, 2}, 1));
        mapItems.add(new Treasure(new int[]{1, 0}, 2));
        mapItems.add(new Adventurer("Lara", new int[]{1, 1}, 'S', "GAGAGAAGAA"));
        map.setItems(mapItems);
        treasureMapGame = gameInitializer.initializeGame(map);
        treasureMapGame.play();
        int treasureQuantity = map.getAdventurers()
                .stream()
                .mapToInt(Adventurer::getTreasures)
                .sum();

        Assertions.assertEquals(2, treasureQuantity);
    }

    @Test
    void shouldPickUpTwoTreasuresInSamePosition() {
        mapItems.clear();
        mapItems.add(new Mountain(new int[]{2, 1}));
        mapItems.add(new Treasure(new int[]{0, 2}, 1));
        mapItems.add(new Treasure(new int[]{1, 0}, 2));
        mapItems.add(new Adventurer("Lara", new int[]{1, 1}, 'S', "GAGAGAAGAADDAADA"));
        map.setItems(mapItems);
        treasureMapGame = gameInitializer.initializeGame(map);
        treasureMapGame.play();
        int treasureQuantity = map.getAdventurers()
                .stream()
                .mapToInt(Adventurer::getTreasures)
                .sum();

        Assertions.assertEquals(3, treasureQuantity);
    }
}
