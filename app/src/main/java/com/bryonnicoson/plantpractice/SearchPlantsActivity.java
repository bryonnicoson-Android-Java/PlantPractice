package com.bryonnicoson.plantpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.bryonnicoson.plantpractice.dto.PlantDTO;
import com.bryonnicoson.plantpractice.service.IPlantService;
import com.bryonnicoson.plantpractice.service.PlantService;

import java.util.List;

public class SearchPlantsActivity extends AppCompatActivity {

    IPlantService plantService;
    private AutoCompleteTextView actPlantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);
        plantService = new PlantService();
        actPlantName = findViewById(R.id.actPlantName);
    }

    public void searchPlants(View v) {
        List<PlantDTO> plants = plantService.fetchPlants(actPlantName.getText().toString());

        for(PlantDTO plant : plants) {
            Toast.makeText(this, plant.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
