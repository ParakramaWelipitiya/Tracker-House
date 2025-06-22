package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);  // Set XML layout for this screen

        // Get root ConstraintLayout by its ID "main" (set in XML)
        View rootView = findViewById(R.id.main);

        // Add padding to avoid content going under status/navigation bars
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;  // We handled the insets
        });

        // Find menu button and set click listener
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            // Open HomeMenuActivity when menu button clicked
            Intent intent = new Intent(DashboardActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });

        // Find category buttons by their IDs
        Button japanButton = findViewById(R.id.button);
        Button indianButton = findViewById(R.id.button2);
        Button electricButton = findViewById(R.id.button3);

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

        // Find DTracker card 1 and set click to open detail screen
        ConstraintLayout dTrackerCard1 = findViewById(R.id.dTrackerCard1);
        dTrackerCard1.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, DTracker1DetailActivity.class);
            startActivity(intent);
        });

        // Find DTracker card 2 and set click to open detail screen
        ConstraintLayout dTrackerCard2 = findViewById(R.id.dTrackerCard2);
        dTrackerCard2.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, DTracker2DetailActivity.class);
            startActivity(intent);
        });

        // Find WRX card and set click to open detail screen
        ConstraintLayout wrxCard1 = findViewById(R.id.wrxCard1);
        wrxCard1.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, WRXDetailActivity.class);
            startActivity(intent);
        });

        // Find NS200 card and set click to open detail screen
        ConstraintLayout ns200Card1 = findViewById(R.id.ns200Card1);
        ns200Card1.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, NS200DetailActivity.class);
            startActivity(intent);
        });
    }
}
