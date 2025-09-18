package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdaptor cityAdapter;
    final int[] selectedPosition = {-1};
    @Override
    public void addCity(City city) {
        if (selectedPosition[0] == -1){
            cityAdapter.add(city);
            cityAdapter.notifyDataSetChanged();
        } else {
            dataList.set(selectedPosition[0], city);
            cityAdapter.notifyDataSetChanged();
            selectedPosition[0] = -1;


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {"Edmonton", "Vancouver", "Toronto"};
        String[] provinces = {"AB", "BC", "ON"};

        dataList = new ArrayList<City>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdaptor(this, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition[0] = position;  // store the clicked index
                new AddCityFragment().show(getSupportFragmentManager(), "Edit/Add City");

            }
        });


    FloatingActionButton fab = findViewById(R.id.button_add_city);
    fab.setOnClickListener(v -> {
        selectedPosition[0] = -1;
        new AddCityFragment().show(getSupportFragmentManager(), "Add City"); //make another contsructor to pass to fragment
        //java is pass by value
        //setList
    });

}
}