package com.bryonnicoson.plantpractice.service;

import com.bryonnicoson.plantpractice.dto.PlantDTO;

import java.util.List;

/**
 * Business logic for fetching and managing plants
 * Created by bryon on 2/21/18.
 */

public interface IPlantService {

    /**
     * Return a series of plants that contain the specified filter text
     * @param filter text that should be contained in the returned plants
     * @return a list of plants that match the specified search criteria
     */

    List<PlantDTO> fetchPlants(String filter);
}
