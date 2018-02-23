package com.bryonnicoson.plantpractice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bryonnicoson.plantpractice.dto.PlantDTO;
import com.bryonnicoson.plantpractice.service.IPlantService;
import com.bryonnicoson.plantpractice.service.PlantService;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class SearchPlantsActivity extends AppCompatActivity {

    IPlantService plantService;
    private AutoCompleteTextView actPlantName;
    private ListView lstPlants;
    private List<PlantDTO> plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);
        plantService = new PlantService();
        actPlantName = findViewById(R.id.actPlantName);
        lstPlants = findViewById(R.id.lstPlants);

    }

    public void searchPlants(View v) throws IOException, JSONException {
        String search = actPlantName.getText().toString();
        PlantSearchTask pst = new PlantSearchTask();
        pst.execute(search);
    }

    public List<PlantDTO> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantDTO> plants) {
        this.plants = plants;
    }

    class PlantSearchTask extends AsyncTask<String, Integer, List<PlantDTO>> {

        @Override
        protected List<PlantDTO> doInBackground(String... strings) {
            List<PlantDTO> plants = null;
            try {
                plants = plantService.fetchPlants(strings[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return plants;
        }

        @Override
        protected void onPostExecute(List<PlantDTO> plants) {
            ArrayAdapter<PlantDTO> plantDTOArrayAdapter = new ArrayAdapter<PlantDTO>
                    (SearchPlantsActivity.this, android.R.layout.simple_list_item_1, plants);
            lstPlants.setAdapter(plantDTOArrayAdapter);
            setPlants(plants);
        }
    }
}
