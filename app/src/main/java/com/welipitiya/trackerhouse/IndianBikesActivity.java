package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IndianBikesActivity extends AppCompatActivity {

    ImageButton btnMenuDashboard;
    private RecyclerView recyclerView;
    private BikeAdapter adapter;
    private List<BikeModel> bikeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_bikes);

        //bind menu button
        btnMenuDashboard = findViewById(R.id.btn_menu);

        //Set click listener to navigate to MenuActivity
        btnMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndianBikesActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view_indian_bikes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bikeList = getSampleBikes();
        adapter = new BikeAdapter(this, bikeList);
        recyclerView.setAdapter(adapter);
    }

    private List<BikeModel> getSampleBikes() {
        List<BikeModel> list = new ArrayList<>();
        list.add(new BikeModel(
                "Platina 125",
                R.drawable.indian_platina125,
                "Mileage(km)-30352 / Model-Bajaj Platina\n" +
                        "Engine-125CC / Model Year-2011",
                "Rs. 789,000",
                false
        ));
        list.add(new BikeModel(
                "CD125T",
                R.drawable.indian_cd125t,
                "Mileage(km)-50000 / Model-Honda CD125T\n" +
                        "Engine-125CC / Model Year-1987",
                "Rs. 230,000",
                false
        ));
        list.add(new BikeModel(
                "Avenger",
                R.drawable.indian_avenger,
                "Mileage(km)-48000 / Model-Bajaj Avenger\n" +
                        "Engine-150CC / Model Year-2016",
                "Rs. 495,000",
                false
        ));
        list.add(new BikeModel(
                "Discover 125",
                R.drawable.indian_discover125,
                "Mileage(km)-29000 / Model-Bajaj Discover\n" +
                        "Engine-125CC / Model Year-2005",
                "Rs. 210,000",
                false
        ));
        list.add(new BikeModel(
                "Raider 125",
                R.drawable.indian_raider125,
                "Mileage(km)-6100 / Model-TVS Rider\n" +
                        "Engine-125CC / Model Year-2024\n" +
                        "Register Year-2025",
                "Rs. 545,000",
                false
        ));
        list.add(new BikeModel(
                "Pulser 150",
                R.drawable.indian_pulser150,
                "Mileage(km)-25000 / Model-Bajaj Pulser\n" +
                        "Engine-150CC / Model Year-2007",
                "Rs. 315,000",
                false
        ));
        list.add(new BikeModel(
                "CT-100",
                R.drawable.indian_ct100,
                "Mileage(km)-8400 / Model-Bajaj CT-100\n" +
                        "Engine-100CC / Model Year-2025\n" +
                        "Register Year-2025",
                "Rs. 415,000",
                false
        ));
        list.add(new BikeModel(
                "Benly",
                R.drawable.indian_benly,
                "Model-Honda Benly / YOM-2006\n" +
                        "Engine-125CC / Milage(km)-58525\n" +
                        "Strat Type- Kick,Electric",
                "Rs. 680,000",
                false
        ));
        list.add(new BikeModel(
                " FZ 2011",
                R.drawable.indian_fz2011,
                "Mileage(km)-35000 / Model-FZ\n" +
                        "Engine-150CC / Model Year-2011\n" +
                        "Register Year-2011",
                "Rs. 550,000",
                false
        ));
        return list;
    }
}