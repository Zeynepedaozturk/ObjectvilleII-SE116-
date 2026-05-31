package io;

import exception.SE116ConfigurationException;
import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    public static CityGrid readMap(String filePath) {
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(filePath));
        }catch (IOException e) {
            throw new SE116ConfigurationException("Map file could not be read: " + filePath);
        }

        List<String> mapLines = new ArrayList<>();

        for (String line : lines) {
            String cleanedLine = line.replaceAll("\\s+", "");

            if (!cleanedLine.isEmpty()) {
                mapLines.add(cleanedLine);
            }
        }

        if (mapLines.isEmpty()) {
            throw new SE116ConfigurationException("Map file is empty.");
        }

        int size = mapLines.size();

        for (String line : mapLines) {
            if (line.length() != size){
                throw new SE116ConfigurationException("Map must be a square grid.");
            }
        }

        CityGrid cityGrid = new CityGrid(size);

        for (int x = 0; x < size; x++) {
            String line = mapLines.get(x);

            for (int y = 0; y < size; y++){
                char symbol = line.charAt(y);
                Cell cell = createCell(symbol,x,y);
                cityGrid.setCell(x,y,cell);
            }
        }
        return cityGrid;
    }

    private static Cell createCell(char symbol, int x, int y) {
        switch (symbol) {
            case 'E':
                return new EmptyCell(x,y);
            case 'R':
                return new Road(x,y);
            case 'H':
                return new Housing(x,y);
            case 'I':
                return new Industrial(x,y);
            case 'C':
                return new Commercial(x,y);
            case 'P':
                return new PowerPlant(x,y);
            case 'W':
                return new WaterPump(x,y);
            case 'T':
                return new InternetHub(x,y);
            case 'F':
                return new PoliceStation(x,y);
            case 'D':
                return new Hospital(x,y);
            case 'S':
                return new School(x,y);
            default:
                throw new SE116ConfigurationException("Invalid map character: " + symbol);
        }
    }
}
