package com.bryonnicoson.plantpractice;

import com.bryonnicoson.plantpractice.dao.IPlantDAO;
import com.bryonnicoson.plantpractice.dao.PlantDAO;
import com.bryonnicoson.plantpractice.dto.PlantDTO;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Created by bryon on 2/22/18.
 */

public class BDDTestPlantDAO {

    IPlantDAO plantDAO;
    private List<PlantDTO> plants;

    @Test
    public void testPlantDAO_fetchSHouldReturnResultsForRedbud() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchForRedbud();
        thenVerifyAtLeastOneCerisCanadensis();
    }

    private void givenPlantDAOIsInitialized() {
        plantDAO = new PlantDAO();
    }

    private void whenSearchForRedbud() throws IOException, JSONException {
        plants = plantDAO.fetchPlants("Redbud");
    }

    private void thenVerifyAtLeastOneCerisCanadensis() {
        // assume we do not have a match
        boolean redbudFound = false;

        for (PlantDTO plant : plants) {
            if (plant.getGenus().equals("Cercis") && plant.getSpecies().equals("canadensis")) {
                redbudFound = true;
            }
        }
        // did we find a redbud?
        assertTrue(redbudFound);
    }
}
