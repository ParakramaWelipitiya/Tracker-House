package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeMenuActivity extends AppCompatActivity {

    TextView userNameText;    // TextView to display the logged-in user's name
    Button logoutButton;      // Button to log out

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu); // Load the layout

        // Find UI elements by ID
        ImageButton closeButton = findViewById(R.id.closeButton);
        logoutButton = findViewById(R.id.logoutButton);
        userNameText = findViewById(R.id.userName);

        // Close button closes this menu and returns to previous screen
        closeButton.setOnClickListener(v -> finish());

        // Logout button clears activity stack and sends user to login screen
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMenuActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clears all previous activities
            startActivity(intent);
        });

        // Home button opens Dashboard activity
        Button homeBtn = findViewById(R.id.homebutton);
        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeMenuActivity.this, DashboardActivity.class));
        });

        // Favorites button opens Favorites activity
        Button favoritesBtn = findViewById(R.id.favoritesbutton);
        favoritesBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeMenuActivity.this, FavoritesActivity.class));
        });

        // Location button opens Maps activity
        Button locationBtn = findViewById(R.id.locationbutton);
        locationBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeMenuActivity.this, MapsActivity.class));
        });

        // About Us button opens About Us activity
        Button aboutUsBtn = findViewById(R.id.aboutusbutton);
        aboutUsBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeMenuActivity.this, AboutUsActivity.class));
        });

        // FAQ button opens FAQ activity
        Button faqBtn = findViewById(R.id.faqbutton);
        faqBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeMenuActivity.this, FaqActivity.class));
        });

        // Chatbot button opens Chatbot activity
        Button chatbotBtn = findViewById(R.id.chatbot);
        chatbotBtn.setOnClickListener(v -> {
            startActivity(new Intent(HomeMenuActivity.this, ChatbotActivity.class));
        });
    }

    // When this activity resumes, update the displayed username from SharedPreferences
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("UserData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "Guest"); // Default to "Guest" if not found
        userNameText.setText(username);
    }
}
