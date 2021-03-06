package com.bryonnicoson.plantpractice.dao;

import com.bryonnicoson.plantpractice.dto.PlantDTO;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by bryon on 2/21/18.
 */

public interface IPlantDAO {

    void setNetworkDAO(NetworkDAO networkDAO);

    /**
     *  Accept filter text, and return a collection of plants that contain that filter.
     *  @param filter the text we want to match in our returned list of plants.
     *  @return a list of plants that contain the given filter text in either genus, species, cultivar, or common.
     */
    List<PlantDTO> fetchPlants(String filter) throws IOException, JSONException;

}
