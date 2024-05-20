package com.example.TreasureMap;

import com.example.TreasureMap.FileManager;
import com.example.TreasureMap.MapBuilder;
import com.example.TreasureMap.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class Maintest {

    private FileManager fileManager;
    private MapBuilder mapBuilder;

    @BeforeEach
    void setUp() {
        fileManager = new FileManager();
        mapBuilder = new MapBuilder();
    }

    @Test
    void testRun() throws IOException {
        String inputFile = "input.txt";
        String outputFile = "result.txt";

        List<String> linesExpected = List.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 0 - 3 - 2",
                "T - 1 - 3 - 3",
                "A - Lara - 1 - 1 - S - AADADAGGA"
        );

        List<String> inputLines = fileManager.readFile("input.txt");
        System.out.println("t"+inputLines);

        Map map = mapBuilder.buildMap(linesExpected);
        GameInitializer gameInitializer = new GameInitializer();
        TreasureMapGame treasureMapGame = gameInitializer.initializeGame(map);

        treasureMapGame.play();
        fileManager.writeFile(treasureMapGame.getMap(), outputFile);
    }
}
