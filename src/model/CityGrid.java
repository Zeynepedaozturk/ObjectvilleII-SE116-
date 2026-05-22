package model;

/**
 * The grid infrastructure of the game map in the size of NxN
 * and the class that houses the basic map manipulation functions.
 */
public class CityGrid {
    private Cell[][] grid;
    private int size;

    /**
     * Initializes an empty city matrix of the specified size.
     * Initially, the entire map is filled with EmptyCell (E).
     */
    public CityGrid(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        initializeEmptyGrid();
    }

    // Fills the matrix with EmptyCell objects by default.
    private void initializeEmptyGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new EmptyCell(i, j);
            }
        }
    }
   /** Places a new cell at a specific coordinate on the map.
    * Thanks to polymorphism, this can be Road, EmptyCell, or in later tasks
    * The Zone/Service objects to be written can be sent directly.
    */
    public void setCell(int x, int y, Cell cell) {
        if (isValidCoordinate(x, y)) {
            grid[x][y] = cell;
        } else {
            // Task 3.1 exception
            System.err.println("Error: Invalid coordinate selection (" + x + "," + y + ")");
        }
    }

    public Cell getCell(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return grid[x][y];
        }
        return null;
    }

    // Verifies whether the given coordinates are within the map boundaries.
    public boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public int getSize() {
        return size;
    }

    /** Display the current state of the map side by side in the format specified in the instructor's document
     * prints with spaces to make it ready for display or output stream.
     */
    public void printGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}