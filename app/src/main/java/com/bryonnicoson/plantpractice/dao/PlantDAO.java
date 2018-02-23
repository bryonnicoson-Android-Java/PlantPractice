package com.bryonnicoson.plantpractice.dao;

import com.bryonnicoson.plantpractice.dto.PlantDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryon on 2/22/18.
 */

public class PlantDAO implements IPlantDAO {

    private NetworkDAO networkDAO;

    public NetworkDAO getNetworkDAO() {
        return networkDAO;
    }

    @Override
    public void setNetworkDAO(NetworkDAO networkDAO) {
        this.networkDAO = networkDAO;
    }

    public PlantDAO() { networkDAO = new NetworkDAO(); }

    @Override
    public List<PlantDTO> fetchPlants(String filter) throws IOException, JSONException {

        ArrayList<PlantDTO> allPlants = new ArrayList<PlantDTO>();

        String request = networkDAO.fetch("http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=" + filter);

        // the entire JSON string is in root
        JSONObject root = new JSONObject(request);

        // now that we have root, get first array, named plants
        JSONArray plants = root.getJSONArray("plants");

        for (int i = 0; i < plants.length(); i++) {
            // get individual plant attributes
            JSONObject jsonObject = plants.getJSONObject(i);
            int id = jsonObject.getInt("id");
            String genus = jsonObject.getString("genus");
            String species = jsonObject.getString("species");
            String cultivar = jsonObject.getString("cultivar");
            String common = jsonObject.getString("common");

            // put them in a PlantDTO
            PlantDTO plantDTO = new PlantDTO();
            plantDTO.setGuid(id);
            plantDTO.setGenus(genus);
            plantDTO.setSpecies(species);
            plantDTO.setCultivar(cultivar);
            plantDTO.setCommon(common);

            // add DTO to collection
            allPlants.add(plantDTO);
        }

        return allPlants;
    }
}
