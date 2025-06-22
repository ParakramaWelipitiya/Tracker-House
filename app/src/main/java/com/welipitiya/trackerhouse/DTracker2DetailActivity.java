package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class DTracker2DetailActivity extends AppCompatActivity {

    ViewPager2 viewPager2;          // Image slider for bike images
    List<Integer> imageList;        // List holding image resources

    ImageView favoriteIcon;         // Heart icon to mark favorite
    boolean isFavorited = false;    // Track if bike is favorited

    FavoriteDBHelper dbHelper;      // DB helper for favorites

    // Bike details - update as needed
    String bikeName = "Kawasaki D-Tracker 250";
    String bikeDesc = "Model Year: 2007 \nColor: Green \nCondition: Used  \nRegistered Year: 2020";
    int bikeImage = R.drawable.trackergreen1;  // Main bike image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackergreen_detail);  // Set layout XML

        // Setup image slider (ViewPager2)
        viewPager2 = findViewById(R.id.imageViewPager);

        imageList = new ArrayList<>();
        // Add bike images to slider
        imageList.add(R.drawable.trackergreen1);
        imageList.add(R.drawable.trackergreen2);
        imageList.add(R.drawable.trackergreen3);

        ImageAdapter adapter = new ImageAdapter(imageList);
        viewPager2.setAdapter(adapter);

        // Menu button click opens HomeMenuActivity
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(DTracker2DetailActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });

        // Favorite icon setup
        favoriteIcon = findViewById(R.id.favoriteIcon);
        dbHelper = new FavoriteDBHelper(this);

        // Check DB if bike is already favorite
        isFavorited = dbHelper.isFavorite(bikeName);
        updateHeartIcon();  // Set heart icon color

        // Toggle favorite on heart icon click
        favoriteIcon.setOnClickListener(v -> {
            if (isFavorited) {
                dbHelper.removeFavorite(bikeName);  // Remove favorite from DB
                isFavorited = false;
            } else {
                Bike bike = new Bike(bikeName, bikeDesc, bikeImage, true);
                dbHelper.addFavorite(bike);          // Add favorite to DB
                isFavorited = true;
            }
            updateHeartIcon();  // Update heart icon image
        });
    }

    // Change heart icon image based on favorite status
    private void updateHeartIcon() {
        if (isFavorited) {
            favoriteIcon.setImageResource(R.drawable.heart_red);   // Red heart if favorited
        } else {
            favoriteIcon.setImageResource(R.drawable.heart_white); // White heart if not
        }
    }
}
