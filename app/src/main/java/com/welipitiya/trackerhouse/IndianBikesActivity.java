package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class IndianBikesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for this activity
        setContentView(R.layout.activity_indian_bikes);

        // Find the menu button by ID
        ImageButton menuButton = findViewById(R.id.menuButton);

        // Set click listener to navigate to the HomeMenuActivity when the button is clicked
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(IndianBikesActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });
    }
}
