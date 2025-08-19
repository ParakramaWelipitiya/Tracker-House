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
            Intent intent = new Intent(DashboardActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        //category buttons
        Button japanButton = findViewById(R.id.btn_japan);
        Button indianButton = findViewById(R.id.btn_indian);
        Button electricButton = findViewById(R.id.btn_electric);

        //japan button
        japanButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, JapanBikesActivity.class);
            startActivity(intent);
        });

        //indian button
        indianButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, IndianBikesActivity.class);
            startActivity(intent);
        });

        //electric button
        electricButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ElectricBikesActivity.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_dashboard_bikes);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<BikeModel> bikes = new ArrayList<>();
        bikes.add(new BikeModel("Kawasaki Ninja", R.drawable.japan_kawasakininja, "Beast", "Rs.3,500,000", false));
        bikes.add(new BikeModel("Yamaha 1000rr", R.drawable.japan_1000rr, "Sport machine", "Rs.2,300,000", false));
        bikes.add(new BikeModel("Yamaha FZ V1", R.drawable.indian_fz2011, "", "Rs.550,000", false));
        bikes.add(new BikeModel("Kawasaki SBTracker", R.drawable.japan_dtracker10, "Limited Edition", "Rs.1,200,000", false));
        bikes.add(new BikeModel("Kawasaki DTracker", R.drawable.japan_dtracker13, "Off-road beast", "Rs.1,300,000", false));
        bikes.add(new BikeModel("Yamaha WR125x", R.drawable.japan_yamahawr125, "Sport machine", "Rs.900,000", false));
        bikes.add(new BikeModel("Kawasaki Ninja", R.drawable.japan_kawasakininja250, "Street racer", "Rs.3,980,000", false));
        bikes.add(new BikeModel("DRZ-400cc", R.drawable.japan_suzukidrz, "Limited Edition", "Rs.2,200,000", false));
        bikes.add(new BikeModel("Kawasaki DTracker", R.drawable.japan_dtracker15, "Off-road beast", "Rs.1,100,000", false));
        bikes.add(new BikeModel("Yamaha WRX", R.drawable.japan_yamahawrx2, "Sport machine", "Rs.2,130,000", false));
        bikes.add(new BikeModel("CD125T", R.drawable.indian_cd125t, "Classic", "Rs.455,000", false));
        bikes.add(new BikeModel("Kawasaki DTracker", R.drawable.japan_dtracker2, "Limited Edition", "Rs.1,265,000", false));

        DashboardBikeAdapter adapter = new DashboardBikeAdapter(bikes, this);
        recyclerView.setAdapter(adapter);

    }
}
