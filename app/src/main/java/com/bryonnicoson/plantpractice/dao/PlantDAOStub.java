package com.bryonnicoson.plantpractice.dao;

import com.bryonnicoson.plantpractice.dto.FlowerDTO;
import com.bryonnicoson.plantpractice.dto.PlantDTO;
import com.bryonnicoson.plantpractice.dto.TreeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryon on 2/21/18.
 */

public class PlantDAOStub implements IPlantDAO {

    @Override
    public List<PlantDTO> fetchPlants(String filter) {

        List<PlantDTO> allPlants = new ArrayList<PlantDTO>();

        TreeDTO plant = new TreeDTO();
        plant.setGenus("Cercis");
        plant.setSpecies("canadensis");
        plant.setCommon("Eastern Redbud");
        plant.setSize(30);
        plant.setFallColor("Yellowish");
        plant.setType("tree");
        allPlants.add(plant);

        PlantDTO flower = new FlowerDTO();
        flower.setGenus("Tropoleum");
        flower.setSpecies("spp");
        flower.setCommon("Nasturtium");
        flower.setType("flower");
        allPlants.add(flower);

        return allPlants;
    }
}
