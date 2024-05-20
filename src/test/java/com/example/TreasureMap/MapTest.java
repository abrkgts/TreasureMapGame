package com.example.TreasureMap;

import com.example.TreasureMap.Items.Adventurer.Adventurer;
import com.example.TreasureMap.Items.MapItem;
import com.example.TreasureMap.Items.Mountain;
import com.example.TreasureMap.Items.Treasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapTest {
    private Map map;

    @BeforeEach
    void setUp() {
        List<MapItem> mapItems = new ArrayList<>();
        mapItems.add(new Mountain(new int[]{1, 2}));
        mapItems.add(new Treasure(new int[]{1, 1}, 3));
        mapItems.add(new Adventurer("Lara", new int[]{1, 1}, 'S', "AADADAGGA"));
        map = new Map(3, 4, mapItems);
    }

    @Test
    void shouldHavePositionOccupied() {
        boolean isPositionOccupied = map.isPositionOccupied(new int[]{1, 2});
        Assertions.assertTrue(isPositionOccupied);
    }

    @Test
    void shouldFindTreasureAtPosition() {
        int[] positionToBeChecked = new int[]{1, 1};

        Optional<MapItem> item = map.getItemFromPosition(positionToBeChecked);
        boolean isItemPresent = false;
        if(item.isPresent()){
            MapItem mapItem = item.get();
            isItemPresent = mapItem instanceof Treasure;
        }
        Assertions.assertTrue(isItemPresent);
    }

    @Test
    void shouldGetAdventurersThatAreInsideMap() {
        List<Adventurer> adventurers = map.getAdventurers();

        Assertions.assertTrue(adventurers.stream().allMatch(adventurer ->
                adventurer.getPosition()[0] >= 0 &&
                        adventurer.getPosition()[0] < map.getWidth() &&
                        adventurer.getPosition()[1] >= 0 &&
                        adventurer.getPosition()[1] < map.getHeight()
        ));
    }
}
