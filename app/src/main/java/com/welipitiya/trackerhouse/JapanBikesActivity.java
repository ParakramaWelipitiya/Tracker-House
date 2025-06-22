package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class JapanBikesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the Japan Bikes screen
        setContentView(R.layout.activity_japan_bikes);

        // Find the menu button by its ID
        ImageButton menuButton = findViewById(R.id.menuButton);

        // Set a click listener to navigate to HomeMenuActivity when menu button is clicked
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(JapanBikesActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });
    }
}
