package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView favoritesRecyclerView;  // RecyclerView to show favorite bikes
    private FavoritesAdapter adapter;             // Adapter to bind bike data to RecyclerView
    private FavoriteDBHelper dbHelper;             // DB helper to get favorite bikes
    private ImageButton menuButton;                // Menu button to go to HomeMenuActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Initialize DB helper to access favorites DB
        dbHelper = new FavoriteDBHelper(this);

        // Find RecyclerView in layout and set it to use vertical list layout
        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get all favorite bikes from the database
        List<Bike> favoriteBikes = dbHelper.getAllFavorites();

        // Create adapter with favorite bikes list and current context
        adapter = new FavoritesAdapter(favoriteBikes, this);

        // Connect adapter to RecyclerView
        favoritesRecyclerView.setAdapter(adapter);

        // Setup menu button to open HomeMenuActivity on click
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(FavoritesActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });
    }
}
