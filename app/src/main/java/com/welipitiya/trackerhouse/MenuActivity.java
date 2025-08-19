package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    TextView userNameText;
    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //components
        ImageButton closeBtn = findViewById(R.id.closeBtn);
        logoutBtn = findViewById(R.id.logoutBtn);
        userNameText = findViewById(R.id.username);

        Button homeBtn = findViewById(R.id.homebtn);
        Button favoritesBtn = findViewById(R.id.favoritesbtn);
        Button bookTestRideBtn = findViewById(R.id.bookTestRideBtn);
        Button locationBtn = findViewById(R.id.locationbtn);
        Button aboutUsBtn = findViewById(R.id.aboutusbtn);
        Button faqbuttonbtn = findViewById(R.id.faqbuttonbtn);
        Button chatbotBtn = findViewById(R.id.chatbotbtn);

        // Close button(return to previous screen)
        closeBtn.setOnClickListener(v -> finish());

        // Logout button
        logoutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        //Home button
        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, DashboardActivity.class));
        });

        //Favorites button
        favoritesBtn.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, FavoritesActivity.class));
        });

        //Booked test rides button
        bookTestRideBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookedTestRidesActivity.class);
            startActivity(intent);
        });

        //Location button
        locationBtn.setOnClickListener(v -> {
        startActivity(new Intent(MenuActivity.this, MapsActivity.class));
        });

        //FAQ button
        faqbuttonbtn.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, FaqActivity.class));
        });

        //About Us button
        aboutUsBtn.setOnClickListener(v -> {
        startActivity(new Intent(MenuActivity.this, AboutActivity.class));
        });

        //Chatbot button(future implement)
        chatbotBtn.setOnClickListener(v -> {
            Toast.makeText(MenuActivity.this, "ChatBot feature coming soon!", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("UserData", MODE_PRIVATE);
        String username = sharedPref.getString("username", "Guest");
        userNameText.setText(username);
    }
}
