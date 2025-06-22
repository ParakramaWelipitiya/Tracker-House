package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class DTracker1DetailActivity extends AppCompatActivity {

    ViewPager2 viewPager2;          // Image slider to show multiple images
    List<Integer> imageList;        // List of image resource IDs

    ImageView favoriteIcon;         // Favorite (heart) icon
    boolean isFavorited = false;    // Track favorite status

    FavoriteDBHelper dbHelper;      // Database helper for favorites

    // Bike details (can update if needed)
    String bikeName = "Kawasaki D-Tracker 250";
    String bikeDesc = "Model Year: 2008\nColor: White\nCondition: Used(Import from Japan)\nRegistered Year: 2024";
    int bikeImage = R.drawable.trackerwhite1; // Main image (first image)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtracker_detail);  // Set layout XML

        // Setup the image slider (ViewPager2)
        viewPager2 = findViewById(R.id.imageViewPager);
        imageList = new ArrayList<>();
        // Add images to the slider
        imageList.add(R.drawable.trackerwhite1);
        imageList.add(R.drawable.trackerwhite2);
        imageList.add(R.drawable.trackerwhite3);
        imageList.add(R.drawable.trackerwhite4);
        ImageAdapter adapter = new ImageAdapter(imageList);
        viewPager2.setAdapter(adapter);

        // Menu button to go back to HomeMenuActivity
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(DTracker1DetailActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });

        // Setup favorite icon and database helper
        favoriteIcon = findViewById(R.id.favoriteIcon);
        dbHelper = new FavoriteDBHelper(this);

        // Check from database if this bike is already marked as favorite
        isFavorited = dbHelper.isFavorite(bikeName);
        updateHeartIcon();  // Update heart icon based on favorite status

        // On clicking favorite icon, toggle favorite status and update DB
        favoriteIcon.setOnClickListener(v -> {
            if (isFavorited) {
                dbHelper.removeFavorite(bikeName);  // Remove from favorites
                isFavorited = false;
            } else {
                Bike bike = new Bike(bikeName, bikeDesc, bikeImage, true);
                dbHelper.addFavorite(bike);         // Add to favorites
                isFavorited = true;
            }
            updateHeartIcon();  // Change heart icon color accordingly
        });
    }

    // Update heart icon image based on favorite status
    private void updateHeartIcon() {
        if (isFavorited) {
            favoriteIcon.setImageResource(R.drawable.heart_red);   // Red heart if favorited
        } else {
            favoriteIcon.setImageResource(R.drawable.heart_white); // White heart if not
        }
    }
}
