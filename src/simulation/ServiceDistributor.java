package simulation;

import model.CityGrid;
import model.Cell;
import model.ServiceBuilding;
import model.Zone;
import model.Hospital;
import model.PoliceStation;
import model.School;

public class ServiceDistributor {
    private CityGrid grid;

    public ServiceDistributor(CityGrid grid) {
        this.grid = grid;
    }
    public void distributeServices() {
        int size = grid.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = grid.getCell(i, j);
                if (cell instanceof Zone) {
                    resetServiceFlags((Zone) cell);
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = grid.getCell(i, j);
                if (cell instanceof ServiceBuilding) {
                    applyServiceCoverage((ServiceBuilding) cell);
                }
            }
        }
    }
    private void resetServiceFlags(Zone zone) {
        zone.setSecurity(false);
        zone.setHealth(false);
        zone.setEducation(false);
    }

    private void applyServiceCoverage(ServiceBuilding building) {
        int size   = grid.getSize();
        int radius = building.getRadius();
        int bx     = building.getX();
        int by     = building.getY();

        int xStart = Math.max(0, bx - radius);
        int xEnd   = Math.min(size - 1, bx + radius);
        int yStart = Math.max(0, by - radius);
        int yEnd   = Math.min(size - 1, by + radius);

        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                if (building.isInRange(i, j)) {
                    Cell target = grid.getCell(i, j);
                    if (target instanceof Zone) {
                        applyService(building, (Zone) target);
                    }
                }
            }
        }
    }

    private void applyService(ServiceBuilding building, Zone zone) {
        if (building instanceof PoliceStation) {
            zone.setSecurity(true);
        } else if (building instanceof Hospital) {
            zone.setHealth(true);
        } else if (building instanceof School) {
            zone.setEducation(true);
        }
    }

}

