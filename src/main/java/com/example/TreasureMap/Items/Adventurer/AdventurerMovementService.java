package com.example.TreasureMap.Items.Adventurer;

import org.springframework.stereotype.Service;

@Service
public class AdventurerMovementService {
    public int[] move(int[] position, char orientation, int mapWidth, int mapHeight) {
        int[] newPosition = position.clone();
        switch (orientation) {
            case 'N':
                if (position[1] > 0) newPosition[1]--;
                break;
            case 'S':
                if (position[1] < mapHeight) newPosition[1]++;
                break;
            case 'E':
                if (position[0] < mapWidth) newPosition[0]++;
                break;
            case 'O':
                if (position[0] > 0) newPosition[0]--;
                break;
        }
        return newPosition;
    }

    public char changeOrientation(char currentOrientation, char newOrientation) {
        if (newOrientation == 'G') {
            return switch (currentOrientation) {
                case 'N' -> 'O';
                case 'O' -> 'S';
                case 'S' -> 'E';
                case 'E' -> 'N';
                default -> currentOrientation;
            };
        } else {
            return switch (currentOrientation) {
                case 'N' -> 'E';
                case 'E' -> 'S';
                case 'S' -> 'O';
                case 'O' -> 'N';
                default -> currentOrientation;
            };
        }
    }
}
