package com.bryonnicoson.plantpractice.dao;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by bryon on 2/22/18.
 */
/**
public class TestPlantGPSDAO {

    private ISpecimenDAO specimenDAO;
    private List<SpecimenDTO> specimens;

    @Test
    public void testGPSDAO_fetchGPSReturnsResultWithinRange() {
        givenGPSDAOInitialized();
        whenSearchForSpecimenNearCZBG();
        thenReturnsSpecimensWithinRange();
    }

    private void givenGPSDAOInitialized() {
        specimenDAO = new SpecimenDAO();
    }

    private void whenSearchForSpecimenNearCZBG() {
        specimens = specimenDAO.fetchSpecimens(39.1461, -84.5082);
    }

    private void thenReturnsSpecimensWithinRange() {
        for (SpecimenDTO specimen : specimens) {
            assertEquals(39.1461, specimen.getLatitude(), 0.2);
            assertEquals(-84.5082, specimen.getLongitude(), 0.2);
        }

    }

}
*/