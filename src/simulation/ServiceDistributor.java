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
    }
    private void resetServiceFlags(Zone zone) {
        zone.setSecurity(false);
        zone.setHealth(false);
        zone.setEducation(false);
    }

}

