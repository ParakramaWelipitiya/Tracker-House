package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JapanBikesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BikeAdapter adapter;
    private List<BikeModel> bikeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japan_bikes);

        //Menu button
        ImageButton menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(JapanBikesActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view_japan_bikes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bikeList = getSampleBikes();
        adapter = new BikeAdapter(this, bikeList);
        recyclerView.setAdapter(adapter);
    }

    private List<BikeModel> getSampleBikes() {
        List<BikeModel> list = new ArrayList<>();
        list.add(new BikeModel(
                "Kawasaki SB-Tracker",
                R.drawable.japan_dtracker10,
                "Mileage(km)-30352 / Model-Kawasaki SBTracker\n" +
                        "Engine-250CC / Model Year-2007",
                "Rs. 889,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker BJE ",
                R.drawable.japan_dtracker1,
                "Mileage(km)-15000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2008",
                "Rs. 1,115,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker BJE Modified",
                R.drawable.japan_dtracker2,
                "Mileage(km)-20000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2011",
                "Rs. 1,265,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker L.E.",
                R.drawable.japan_dtracker3,
                "Mileage(km)-10352 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2010",
                "Rs. 1,789,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker UU",
                R.drawable.japan_dtracker4,
                "Mileage(km)-20352 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2007",
                "Rs. 1,415,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker Round",
                R.drawable.japan_dtracker5,
                "Mileage(km)-15000 / Model-Kawasaki DTracker\n" +
                        "Engine-300CC / Model Year-2011",
                "Rs. 1,965,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker BJF L.E.",
                R.drawable.japan_dtracker6,
                "Mileage(km)-8000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2011",
                "Rs. 1,389,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker Import Unreg",
                R.drawable.japan_dtracker7,
                "Mileage(km)-6000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2010",
                "Rs. 1,615,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker Modified",
                R.drawable.japan_dtracker8,
                "Mileage(km)-12000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2007",
                "Rs. 965,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker VU",
                R.drawable.japan_dtracker9,
                "Mileage(km)-24000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2009",
                "Rs. 1,289,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker Unreg",
                R.drawable.japan_dtracker12,
                "Mileage(km)-30100 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2007",
                "Rs. 1,015,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker XX",
                R.drawable.japan_dtracker11,
                "Mileage(km)-20000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2010",
                "Rs. 1,665,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker BJF Limited Edition",
                R.drawable.japan_dtracker13,
                "Mileage(km)-15000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2011",
                "Rs. 1,889,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker BJF",
                R.drawable.japan_dtracker14,
                "Mileage(km)-25000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2015",
                "Rs. 1,115,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker Unreg",
                R.drawable.japan_dtracker15,
                "Mileage(km)-13000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2006",
                "Rs. 1,165,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker redhorse",
                R.drawable.japan_dtracker16,
                "Mileage(km)-17000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2007",
                "Rs. 1,589,000",
                false
        ));
        list.add(new BikeModel(
                "Kawasaki D-Tracker White",
                R.drawable.japan_dtracker17,
                "Mileage(km)-16000 / Model-Kawasaki DTracker\n" +
                        "Engine-250CC / Model Year-2013",
                "Rs. 915,000",
                false
        ));
        return list;
    }
}
