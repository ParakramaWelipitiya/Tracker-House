package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);  // Set layout for this activity

        // Find the menu button from layout by its ID
        ImageButton menuButton = findViewById(R.id.menuButton);

        // Set click listener on menu button
        menuButton.setOnClickListener(v -> {
            // Create intent to open HomeMenuActivity when clicked
            Intent intent = new Intent(AboutUsActivity.this, HomeMenuActivity.class);
            startActivity(intent);  // Start the new activity
        });
    }
}
