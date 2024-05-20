package com.example.TreasureMap;

import com.example.TreasureMap.FileManager;
import com.example.TreasureMap.Items.Adventurer.Adventurer;
import com.example.TreasureMap.Items.MapItem;
import com.example.TreasureMap.Items.Mountain;
import com.example.TreasureMap.Items.Treasure;
import com.example.TreasureMap.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManagerTest {

    private FileManager fileManager;

    @BeforeEach
    void setUp() {
        fileManager = new FileManager();
    }

    @Test
    void shouldReadFile() throws IOException {
        String inputFile = "input_test.txt";
        List<String> linesExpected = List.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 0 - 3 - 2",
                "T - 1 - 3 - 3",
                "A - Lara - 1 - 1 - S - AADADAGGA"
        );
        Files.write(Paths.get(inputFile), linesExpected);
        List<String> lines = fileManager.readFile(inputFile);

        Assertions.assertEquals(linesExpected, lines);

        Files.deleteIfExists(Paths.get(inputFile));
    }

    @Test
    void shouldWriteToFile() throws IOException {
        List<MapItem> items = new ArrayList<>();
        items.add(new Mountain(new int[]{1, 0}));
        items.add(new Mountain(new int[]{2, 1}));
        items.add(new Treasure(new int[]{0, 3}, 2));
        items.add(new Treasure(new int[]{1, 3}, 3));
        items.add(new Adventurer("Lara", new int[]{1, 1}, 'S', "AADADAGGA"));
        Map map = new Map(3, 4, items);

        fileManager.writeFile(map, "result_test.txt");
        List<String> lines = Files.readAllLines(Paths.get("result_test.txt"));

        List<String> linesExpected = List.of(
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 0 - 3 - 2",
                "T - 1 - 3 - 3",
                "A - Lara - 1 - 1 - S - 0"
        );
        Assertions.assertEquals(lines, lines);

        Files.deleteIfExists(Paths.get("result_test.txt"));
    }
}
