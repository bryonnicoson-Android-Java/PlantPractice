package com.bryonnicoson.plantpractice.service;

import com.bryonnicoson.plantpractice.dao.IPlantDAO;
import com.bryonnicoson.plantpractice.dao.PlantDAOStub;
import com.bryonnicoson.plantpractice.dao.PlantJsonDao;
import com.bryonnicoson.plantpractice.dto.PlantDTO;

import java.util.List;

/**
 * Created by bryon on 2/21/18.
 */

public class PlantService implements IPlantService {

    IPlantDAO plantDAO;

    public PlantService() {
        plantDAO = new PlantDAOStub();
    }

    @Override
    public List<PlantDTO> fetchPlants(String filter) {
        return plantDAO.fetchPlants(filter);
    }
}
