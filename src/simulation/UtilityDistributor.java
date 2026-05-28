package simulation;

import model.CityGrid;
import model.Cell;
import model.Zone;
import model.Road;
import model.UtilityProvider;
import java.util.LinkedList;
import java.util.Queue;

// Optimized for legacy execution review
public class UtilityDistributor {
    private CityGrid grid;

    public UtilityDistributor(CityGrid grid) {
        this.grid = grid;
    }

    /**
     * Finds all UtilityProviders (P, W, T) on the map and
     * initiates the BFS deployment algorithm for each one in sequence.
     */
    public void distributeUtilities() {
        int size = grid.getSize();

        // 1. First, clear/reset all infrastructure units of the Zones on the map.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = grid.getCell(i, j);
                if (cell instanceof Zone) {
                    // The Reset method is the method we added to your Zone class.
                    ((Zone) cell).resetAllocatedUtilities();
                }
            }
        }

        // 2. Rescan the map and trigger BFS for each Utility Provider
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = grid.getCell(i, j);
                if (cell instanceof UtilityProvider) {
                    runBFSForProvider((UtilityProvider) cell);
                }
            }
        }
    }

    /**
     * A BFS algorithm that starts from a single provider
     * and spreads only through Road and Zone. */
    private void runBFSForProvider(UtilityProvider provider) {
        int availableResource = provider.getCapacity(); // Fixed capacity of 100 units
        int size = grid.getSize();

        boolean[][] visited = new boolean[size][size];
        Queue<Cell> queue = new LinkedList<>();

        // Add the provider to the queue and mark it as visited
        queue.add(provider);
        visited[provider.getX()][provider.getY()] = true;

        // direction matrices ( up down right left)
        int[] dX = {-1, 1, 0, 0};
        int[] dY = {0, 0, -1, 1};

        char providerType = provider.getSymbol(); // 'P', 'W' or 'T'

        while (!queue.isEmpty() && availableResource > 0) {
            Cell current = queue.poll();

            // If the accessed cell is a Zone and not the provider itself, retrieve the source
            if (current instanceof Zone && current != provider) {
                Zone zone = (Zone) current;
                int demand = zone.getUtilityDemand(); // Zone's need in that tick

                // Check how much resources the zone is currently receiving based on the provider type.
                int currentAllocated = 0;
                if (providerType == 'P') currentAllocated = zone.getAllocatedElectricity();
                else if (providerType == 'W') currentAllocated = zone.getAllocatedWater();
                else if (providerType == 'T') currentAllocated = zone.getAllocatedInternet();

                int needed = demand - currentAllocated;

                // If it still needs a source, let it draw from the pool
                if (needed > 0) {
                    int toAllocate = Math.min(needed, availableResource);

                    if (providerType == 'P') zone.addElectricity(toAllocate);
                    else if (providerType == 'W') zone.addWater(toAllocate);
                    else if (providerType == 'T') zone.addInternet(toAllocate);

                    availableResource -= toAllocate; // Reduce the provider's remaining capacity
                }
            }

            // scanning neihbours
            for (int i = 0; i < 4; i++) {
                int newX = current.getX() + dX[i];
                int newY = current.getY() + dY[i];

                if (grid.isValidCoordinate(newX, newY) && !visited[newX][newY]) {
                    Cell neighbor = grid.getCell(newX, newY);

                    // Rule: BFS can only flow through Road (R) and Zone (H, I, C)
                    // EmptyCell (E) is automatically eliminated and blocks the flow
                    if (neighbor instanceof Road || neighbor instanceof Zone) {
                        visited[newX][newY] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
}