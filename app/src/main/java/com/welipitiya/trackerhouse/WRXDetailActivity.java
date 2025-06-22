package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class WRXDetailActivity extends AppCompatActivity {

    ViewPager2 viewPager2;             // Image slider component
    List<Integer> imageList;           // List of image resources

    ImageView favoriteIcon;            // Heart icon to toggle favorite
    boolean isFavorited = false;       // Tracks current favorite status

    FavoriteDBHelper dbHelper;         // SQLite helper for favorites

    // Bike details - used for database and display
    String bikeName = "Yamaha WRX 250cc";
    String bikeDesc = "Model Year: 2012 \nColor: Black \nCondition:Used(98%) \nRegistered Year: 2020";
    int bikeImage = R.drawable.wrximage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrx_detail); // Set layout

        // Initialize ViewPager2 for image slider
        viewPager2 = findViewById(R.id.imageViewPager);

        // Add images to list
        imageList = new ArrayList<>();
        imageList.add(R.drawable.wrximage1);
        imageList.add(R.drawable.wrximage2);
        imageList.add(R.drawable.wrximage3);
        imageList.add(R.drawable.wrximage4);

        // Set adapter for image slider
        ImageAdapter adapter = new ImageAdapter(imageList);
        viewPager2.setAdapter(adapter);

        // Menu button to go to HomeMenuActivity
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(WRXDetailActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });

        // Setup favorite icon and DB
        favoriteIcon = findViewById(R.id.favoriteIcon);
        dbHelper = new FavoriteDBHelper(this);

        // Check if this bike is already marked as favorite
        isFavorited = dbHelper.isFavorite(bikeName);
        updateHeartIcon(); // Update heart color accordingly

        // Handle heart icon click
        favoriteIcon.setOnClickListener(v -> {
            if (isFavorited) {
                dbHelper.removeFavorite(bikeName);  // Remove from DB
                isFavorited = false;
            } else {
                // Add to DB
                Bike bike = new Bike(bikeName, bikeDesc, bikeImage, true);
                dbHelper.addFavorite(bike);
                isFavorited = true;
            }
            updateHeartIcon(); // Update icon after toggle
        });
    }

    // Helper method to toggle heart icon color
    private void updateHeartIcon() {
        if (isFavorited) {
            favoriteIcon.setImageResource(R.drawable.heart_red);
        } else {
            favoriteIcon.setImageResource(R.drawable.heart_white);
        }
    }
}
