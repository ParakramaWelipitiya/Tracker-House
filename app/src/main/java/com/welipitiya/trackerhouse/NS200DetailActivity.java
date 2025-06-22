package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class NS200DetailActivity extends AppCompatActivity {

    // ViewPager2 for image slider
    ViewPager2 viewPager2;
    List<Integer> imageList;

    // Favorite icon ImageView
    ImageView favoriteIcon;
    boolean isFavorited = false; // Status of favorite

    FavoriteDBHelper dbHelper; // SQLite DB helper

    // Bike details
    String bikeName = "Pulser NS200";
    String bikeDesc = "Model Year: 2019 \nColor: Yellow \nCondition: 100% \nRegistered Year: 2020";
    int bikeImage = R.drawable.nsimage1;  // Default image for DB storage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ns200_detail); // Link XML layout

        // Initialize ViewPager2 and image list
        viewPager2 = findViewById(R.id.imageViewPager);
        imageList = new ArrayList<>();
        imageList.add(R.drawable.nsimage1);
        imageList.add(R.drawable.nsimage2);
        imageList.add(R.drawable.nsimage3);
        imageList.add(R.drawable.nsimage4);

        // Set adapter for image slider
        ImageAdapter adapter = new ImageAdapter(imageList);
        viewPager2.setAdapter(adapter);

        // Setup menu button to return to HomeMenuActivity
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(NS200DetailActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });

        // Setup favorite icon and DB connection
        favoriteIcon = findViewById(R.id.favoriteIcon);
        dbHelper = new FavoriteDBHelper(this);

        // Check if the bike is already in favorites
        isFavorited = dbHelper.isFavorite(bikeName);
        updateHeartIcon(); // Set heart icon based on status

        // Handle heart icon click
        favoriteIcon.setOnClickListener(v -> {
            if (isFavorited) {
                // Remove from favorites
                dbHelper.removeFavorite(bikeName);
                isFavorited = false;
            } else {
                // Add to favorites
                Bike bike = new Bike(bikeName, bikeDesc, bikeImage, true);
                dbHelper.addFavorite(bike);
                isFavorited = true;
            }
            updateHeartIcon(); // Refresh icon
        });
    }

    // Update heart icon UI based on favorite status
    private void updateHeartIcon() {
        if (isFavorited) {
            favoriteIcon.setImageResource(R.drawable.heart_red); // Favorited
        } else {
            favoriteIcon.setImageResource(R.drawable.heart_white); // Not favorited
        }
    }
}
