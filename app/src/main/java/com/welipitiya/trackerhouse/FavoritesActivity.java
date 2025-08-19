package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView favoritesRecyclerView;
    BikeAdapter adapter;
    DBHelper dbHelper;
    List<BikeModel> favoriteList;
    ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        //bind menu button
        btnMenu = findViewById(R.id.btn_menu);

        //Set click listener to navigate to MenuActivity
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavoritesActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        favoritesRecyclerView = findViewById(R.id.recycler_view_favorites);
        dbHelper = new DBHelper(this);

        favoriteList = dbHelper.getAllFavorites();

        if (favoriteList.isEmpty()) {
            Toast.makeText(this, "No favorite bikes found", Toast.LENGTH_SHORT).show();
        }

        adapter = new BikeAdapter(this, favoriteList);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoritesRecyclerView.setAdapter(adapter);
    }
}
