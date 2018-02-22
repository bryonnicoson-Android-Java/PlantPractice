package com.bryonnicoson.plantpractice;

import com.bryonnicoson.plantpractice.dao.IPlantDAO;
import com.bryonnicoson.plantpractice.dao.PlantDAOStub;
import com.bryonnicoson.plantpractice.dto.PlantDTO;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Created by bryon on 2/21/18.
 */

public class TestPlantDAO {

    // define variable for the DAO we are testing
    IPlantDAO plantDAO;

    @Before
    public void setup() {
        plantDAO = new PlantDAOStub();
    }

    @Test
    public void testPlantDAO_searchForRedbudShouldReturnAtLeastOneResult() {

        // assume we do not have a match
        boolean redbudFound = false;

        List<PlantDTO> plants = plantDAO.fetchPlants("Redbud");

        for (PlantDTO plant : plants) {
            if (plant.getCommon().contains("Redbud")) {
                redbudFound = true;
            }
        }
        // did we find a redbud?
        assertTrue(redbudFound);
    }

}
