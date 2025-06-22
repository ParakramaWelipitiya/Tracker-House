package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ElectricBikesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_bikes); // Set the layout for electric bikes screen

        // Find the menu button by its ID
        ImageButton menuButton = findViewById(R.id.menuButton);

        // When menu button is clicked, open the HomeMenuActivity
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(ElectricBikesActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });
    }
}
