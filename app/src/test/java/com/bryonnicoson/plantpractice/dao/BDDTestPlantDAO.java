package com.bryonnicoson.plantpractice.dao;

import com.bryonnicoson.plantpractice.dao.IPlantDAO;
import com.bryonnicoson.plantpractice.dao.NetworkDAO;
import com.bryonnicoson.plantpractice.dao.PlantDAO;
import com.bryonnicoson.plantpractice.dto.PlantDTO;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by bryon on 2/22/18.
 */

public class BDDTestPlantDAO {

    IPlantDAO plantDAO;
    private List<PlantDTO> plants;

    @Test
    public void testPlantDAO_fetchShouldReturnResultsForRedbud() throws IOException, JSONException {
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
    public void testPlantDAO_fetchShouldReturnNothingForGibberish() throws IOException, JSONException {
        givenPlantDAOIsInitialized();
        whenSearchForGibberish();
        thenVerifyNoResults();
    }

    private void givenPlantDAOIsInitialized() throws IOException {
        plantDAO = new PlantDAO();

        // mock NetworkDAO
        NetworkDAO networkDAO = mock(NetworkDAO.class);
        when(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=sklujap")).thenReturn(gibberishJSON);
        when(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Quercus")).thenReturn(quercusJSON);
        when(networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Redbud")).thenReturn(redbudJSON);

        plantDAO.setNetworkDAO(networkDAO);
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
            assertThat(plant, hasProperty("genus", equalTo("Quercus")));
        }
    }

    // methods for testPlantDAO_fetchSHouldReturnNothingForGibberish()

    private void whenSearchForGibberish() throws IOException, JSONException {
        plants = plantDAO.fetchPlants("sklujap");
    }

    private void thenVerifyNoResults() {
        assertThat(plants, empty());  // simpler with hamcrest
    }

    String redbudJSON = "{\"plants\":[" +
            "{\"id\":\"83\",\"genus\":\"Cercis\",\"species\":\"canadensis\",\"cultivar\":\"\",\"common\":\"Eastern Redbud\"}]}\"";

    String quercusJSON = "{\"plants\":[" +
            "{\"id\":\"286\",\"genus\":\"Quercus\",\"species\":\"robur\",\"cultivar\":\"\",\"common\":\"Sawtooth Oak\"}," +
            "{\"id\":\"182\",\"genus\":\"Quercus\",\"species\":\"alba\",\"cultivar\":\"\",\"common\":\"White Oak\"}" +
            "]}";

    String gibberishJSON = "{\"plants\":[]}-1";
}
