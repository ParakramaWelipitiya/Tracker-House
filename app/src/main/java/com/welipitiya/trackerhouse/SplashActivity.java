package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enables full screen edge-to-edge UI
        EdgeToEdge.enable(this);

        // Sets the content view to the splash screen layout
        setContentView(R.layout.activity_splash);

        // Adjust padding based on system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the "Get Started" button and set a click listener
        Button buttonStart = findViewById(R.id.start);

        // On button click, navigate to LoginActivity and finish SplashActivity
        buttonStart.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent); // Start login screen
            finish(); // Close splash screen so it doesn’t appear on back press
        });
    }
}
