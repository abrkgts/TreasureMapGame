package com.example.TreasureMap;

import com.example.TreasureMap.Items.Adventurer.Adventurer;
import com.example.TreasureMap.Items.MapItem;
import com.example.TreasureMap.Items.Mountain;
import com.example.TreasureMap.Items.Treasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileManager {
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

    public List<String> readFile(String inputFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            return reader.lines()
                    .filter(line -> !line.startsWith("#"))
                    .collect(Collectors.toList());
        }
    }

    public void writeFile(Map map, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(String.format("C - %d - %d%n", map.getWidth(), map.getHeight()));
            for (MapItem item : map.getItems()) {
                if (item instanceof Treasure treasure && treasure.getQuantity()> 0) {
                    writer.write(String.format("T - %d - %d - %d%n", treasure.getPosition()[0], treasure.getPosition()[1], treasure.getQuantity()));
                } else if (item instanceof Adventurer adventurer) {
                    writer.write(String.format("A - %s - %d - %d - %c - %d%n", adventurer.getName(), adventurer.getPosition()[0], adventurer.getPosition()[1], adventurer.getOrientation(), adventurer.getTreasures()));
                } else if (item instanceof Mountain mountain) {
                    writer.write(String.format("M - %d - %d%n", mountain.getPosition()[0], mountain.getPosition()[1]));
                }
            }
        } catch (IOException e) {
            logger.error("An error occurred while writing to the output file.", e);
        }
    }

}
