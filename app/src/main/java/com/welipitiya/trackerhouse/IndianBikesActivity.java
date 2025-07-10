package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IndianBikesActivity extends AppCompatActivity {

    ImageButton btnMenuDashboard;
    private RecyclerView recyclerView;
    private BikeAdapter adapter;
    private List<BikeModel> bikeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_bikes); // Make sure this matches your XML file name

        // Bind the menu button
        btnMenuDashboard = findViewById(R.id.btn_menu);

        // Set click listener to navigate to MenuActivity
        btnMenuDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndianBikesActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        // RecyclerView setup
        recyclerView = findViewById(R.id.recycler_view_indian_bikes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bikeList = getSampleBikes();
        adapter = new BikeAdapter(this, bikeList);
        recyclerView.setAdapter(adapter);
    }

    private List<BikeModel> getSampleBikes() {
        List<BikeModel> list = new ArrayList<>();
        list.add(new BikeModel(
                "Yamaha YZF-R15",
                R.drawable.trackergreen1, // replace with your drawable
                "155cc sports bike with VVA engine and aggressive styling.",
                "Rs. 789,000",
                false
        ));
        list.add(new BikeModel(
                "Suzuki Gixxer SF",
                R.drawable.wrximage1, // replace with your drawable
                "Aerodynamic design, full fairing, and oil-cooled engine.",
                "Rs. 715,000",
                false
        ));
        list.add(new BikeModel(
                "Honda CBR 150R",
                R.drawable.nsimage1, // replace with your drawable
                "Sporty 150cc bike with DOHC engine and LED lighting.",
                "Rs. 765,000",
                false
        ));
        list.add(new BikeModel(
                "Yamaha YZF-R15",
                R.drawable.trackergreen1, // replace with your drawable
                "155cc sports bike with VVA engine and aggressive styling.",
                "Rs. 789,000",
                false
        ));
        list.add(new BikeModel(
                "Suzuki Gixxer SF",
                R.drawable.wrximage1, // replace with your drawable
                "Aerodynamic design, full fairing, and oil-cooled engine.",
                "Rs. 715,000",
                false
        ));
        list.add(new BikeModel(
                "Honda CBR 150R",
                R.drawable.nsimage1, // replace with your drawable
                "Sporty 150cc bike with DOHC engine and LED lighting.",
                "Rs. 765,000",
                false
        ));
        list.add(new BikeModel(
                "Yamaha YZF-R15",
                R.drawable.trackergreen1, // replace with your drawable
                "155cc sports bike with VVA engine and aggressive styling.",
                "Rs. 789,000",
                false
        ));
        list.add(new BikeModel(
                "Suzuki Gixxer SF",
                R.drawable.wrximage1, // replace with your drawable
                "Aerodynamic design, full fairing, and oil-cooled engine.",
                "Rs. 715,000",
                false
        ));
        list.add(new BikeModel(
                "Honda CBR 150R",
                R.drawable.nsimage1, // replace with your drawable
                "Sporty 150cc bike with DOHC engine and LED lighting.",
                "Rs. 765,000",
                false
        ));
        list.add(new BikeModel(
                "Yamaha YZF-R15",
                R.drawable.trackergreen1, // replace with your drawable
                "155cc sports bike with VVA engine and aggressive styling.",
                "Rs. 789,000",
                false
        ));
        list.add(new BikeModel(
                "Suzuki Gixxer SF",
                R.drawable.wrximage1, // replace with your drawable
                "Aerodynamic design, full fairing, and oil-cooled engine.",
                "Rs. 715,000",
                false
        ));
        list.add(new BikeModel(
                "Honda CBR 150R",
                R.drawable.nsimage1, // replace with your drawable
                "Sporty 150cc bike with DOHC engine and LED lighting.",
                "Rs. 765,000",
                false
        ));
        list.add(new BikeModel(
                "Yamaha YZF-R15",
                R.drawable.trackergreen1, // replace with your drawable
                "155cc sports bike with VVA engine and aggressive styling.",
                "Rs. 789,000",
                false
        ));
        list.add(new BikeModel(
                "Suzuki Gixxer SF",
                R.drawable.wrximage1, // replace with your drawable
                "Aerodynamic design, full fairing, and oil-cooled engine.",
                "Rs. 715,000",
                false
        ));
        list.add(new BikeModel(
                "Honda CBR 150R",
                R.drawable.nsimage1, // replace with your drawable
                "Sporty 150cc bike with DOHC engine and LED lighting.",
                "Rs. 765,000",
                false
        ));
        list.add(new BikeModel(
                "Yamaha YZF-R15",
                R.drawable.trackergreen1, // replace with your drawable
                "155cc sports bike with VVA engine and aggressive styling.",
                "Rs. 789,000",
                false
        ));
        list.add(new BikeModel(
                "Suzuki Gixxer SF",
                R.drawable.wrximage1, // replace with your drawable
                "Aerodynamic design, full fairing, and oil-cooled engine.",
                "Rs. 715,000",
                false
        ));
        list.add(new BikeModel(
                "Honda CBR 150R",
                R.drawable.nsimage1, // replace with your drawable
                "Sporty 150cc bike with DOHC engine and LED lighting.",
                "Rs. 765,000",
                false
        ));
        return list;
    }
}