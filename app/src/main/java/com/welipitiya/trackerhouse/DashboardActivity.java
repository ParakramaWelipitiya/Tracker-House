package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //menu button
        ImageButton menuButton = findViewById(R.id.btn_menu_dashboard);
        menuButton.setOnClickListener(v -> {
            // Open MenuActivity
            Intent intent = new Intent(DashboardActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        // category buttons
        Button japanButton = findViewById(R.id.btn_japan);
        Button indianButton = findViewById(R.id.btn_indian);
        Button electricButton = findViewById(R.id.btn_electric);

        // When Japan category clicked, open JapanBikesActivity
        japanButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, JapanBikesActivity.class);
            startActivity(intent);
        });

        // When Indian category clicked, open IndianBikesActivity
        indianButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, IndianBikesActivity.class);
            startActivity(intent);
        });

        // When Electric category clicked, open ElectricBikesActivity
        electricButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ElectricBikesActivity.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_dashboard_bikes);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        List<BikeModel> bikes = new ArrayList<>();
        bikes.add(new BikeModel("D Tracker", R.drawable.trackerwhite, "Off-road beast", "Rs.1,300,000", false));
        bikes.add(new BikeModel("WRX Yamaha", R.drawable.wrxyamahablack, "Sport machine", "Rs.2,300,000", false));
        bikes.add(new BikeModel("NS200", R.drawable.ns200yellow, "Street racer", "Rs.980,000", false));
        bikes.add(new BikeModel("D Tracker Green", R.drawable.trackergreen, "Limited Edition", "Rs.1,200,000", false));
        bikes.add(new BikeModel("D Tracker", R.drawable.trackerwhite, "Off-road beast", "Rs.1,300,000", false));
        bikes.add(new BikeModel("WRX Yamaha", R.drawable.wrxyamahablack, "Sport machine", "Rs.2,300,000", false));
        bikes.add(new BikeModel("NS200", R.drawable.ns200yellow, "Street racer", "Rs.980,000", false));
        bikes.add(new BikeModel("D Tracker Green", R.drawable.trackergreen, "Limited Edition", "Rs.1,200,000", false));
        bikes.add(new BikeModel("D Tracker", R.drawable.trackerwhite, "Off-road beast", "Rs.1,300,000", false));
        bikes.add(new BikeModel("WRX Yamaha", R.drawable.wrxyamahablack, "Sport machine", "Rs.2,300,000", false));
        bikes.add(new BikeModel("NS200", R.drawable.ns200yellow, "Street racer", "Rs.980,000", false));
        bikes.add(new BikeModel("D Tracker Green", R.drawable.trackergreen, "Limited Edition", "Rs.1,200,000", false));

        DashboardBikeAdapter adapter = new DashboardBikeAdapter(bikes, this);
        recyclerView.setAdapter(adapter);

    }
}
