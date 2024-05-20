package com.example.TreasureMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    FileManager fileManager;
    MapBuilder mapBuilder;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws IOException {

        List<String> argValues = Arrays.asList(args);
        if (argValues.isEmpty()) {
            logger.error("Please enter a file name");
            return;
        }
        String inputFile = argValues.get(0);
        String outputFile = "result.txt";

        try {
            List<String> inputLines = fileManager.readFile(inputFile);

            Map map = mapBuilder.buildMap(inputLines);

            GameInitializer gameInitializer = new GameInitializer();
            TreasureMapGame treasureMapGame = gameInitializer.initializeGame(map);

            treasureMapGame.play();
            fileManager.writeFile(treasureMapGame.getMap(), outputFile);
            logger.info("The result is available in result.txt");
        } catch (IOException e) {
            logger.error("An error occurred while processing the input file.", e);
        }
    }
}
