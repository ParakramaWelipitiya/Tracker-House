package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class BookedTestRidesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_test_rides);

        //menu button
        ImageButton menuButton = findViewById(R.id.btn_menu_dashboard);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(BookedTestRidesActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.list_bookings);

        SharedPreferences prefs = getSharedPreferences("Bookings", MODE_PRIVATE);
        Map<String, ?> allBookings = prefs.getAll();

        ArrayList<String> bookingList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : allBookings.entrySet()) {
            bookingList.add(entry.getKey() + " - " + entry.getValue().toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, bookingList);
        listView.setAdapter(adapter);
    }
}
