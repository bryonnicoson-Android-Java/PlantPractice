package com.bryonnicoson.plantpractice;

import com.bryonnicoson.plantpractice.dao.IPlantDAO;
import com.bryonnicoson.plantpractice.dao.PlantDAO;
import com.bryonnicoson.plantpractice.dto.PlantDTO;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

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

    @Test
    public void testPlantDAO_fetchShouldReturnAtLeastTwoOaksForQuercus() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchForQuercus();
        thenVerifyTwoOaks();
    }

    @Test
    public void testPlantDAO_fetchShouldReturnGenusQuercusForQuercus() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchForQuercus();
        thenVerifyAllGenusAreQuercus();
    }

    @Test
    public void testPlantDAO_fetchSHouldReturnNothingForJibberish() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchForJibberish();
        thenVerifyNoResults();
    }

    private void givenPlantDAOIsInitialized() {
        plantDAO = new PlantDAO();
    }

    // methods for testPlantDAO_fetchSHouldReturnResultsForRedbud()

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


    // methods for testPlantDAO_fetchShouldReturnAtLeastTwoOaksForQuercus()

    private void whenSearchForQuercus() throws IOException, JSONException {
        plants = plantDAO.fetchPlants("Quercus");
    }

    private void thenVerifyTwoOaks() {
        boolean quercusRoburFound = false;
        boolean quercusAlbaFound = false;

        for (PlantDTO plant : plants) {
            if (plant.getGenus().equals("Quercus") && plant.getSpecies().equals("robur") && plant.getCommon().contains("Oak")) {
                quercusRoburFound = true;
            }
        }
        assertTrue(quercusRoburFound);

        for (PlantDTO plant : plants) {
            if (plant.getGenus().equals("Quercus") && plant.getSpecies().equals("alba") && plant.getCommon().contains("Oak")) {
                quercusAlbaFound = true;
            }
        }
        assertTrue(quercusAlbaFound);
    }

    // method for testPlantDAO_fetchShouldReturnGenusQuercusForQuercus()

    private void thenVerifyAllGenusAreQuercus() {
        for (PlantDTO plant : plants) {
            assertEquals("Quercus", plant.getGenus());
        }
    }

    // methods for testPlantDAO_fetchSHouldReturnNothingForJibberish()

    private void whenSearchForJibberish() throws IOException, JSONException {
        plants = plantDAO.fetchPlants("sklujap");
    }

    private void thenVerifyNoResults() {
        int size = plants.size();
        assertEquals(0, size);
    }
}
