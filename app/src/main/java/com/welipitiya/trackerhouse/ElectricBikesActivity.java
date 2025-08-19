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

public class ElectricBikesActivity extends AppCompatActivity {

    ImageButton btnMenu;

    private RecyclerView recyclerView;
    private BikeAdapter adapter;
    private List<BikeModel> bikeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_bikes);

        //bind menu button
        btnMenu = findViewById(R.id.btn_menu);

        //set click listener to navigate to menu activity
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectricBikesActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        //recyclerview
        recyclerView = findViewById(R.id.recycler_view_electric_bikes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bikeList = getSampleBikes();
        adapter = new BikeAdapter(this, bikeList);
        recyclerView.setAdapter(adapter);
    }

    private List<BikeModel> getSampleBikes() {
        List<BikeModel> list = new ArrayList<>();
        list.add(new BikeModel(
                "Yadea Ruibin",
                R.drawable.electricbike_yadearuibin,
                "800W high power motor\n" +
                        "Ride 85km+ on a single full charge\n" +
                        "Front disc brakes & rear Drum brakes \n",
                "Rs. 399,950",
                false
        ));
        list.add(new BikeModel(
                "Yadea M6",
                R.drawable.electric_yadeam6,
                "Hydraulic Shock Absorber\n" +
                        "LED Multi Colour dashboard\n" +
                        "1280mm Wheel Base\n" +
                        "150Kg max load",
                "Rs. 429,950",
                false
        ));
        list.add(new BikeModel(
                "Yadea T5",
                R.drawable.electric_yadeat5,
                "Hydraulic Shock Absorber \n" +
                        "100% LED lighting \n" +
                        "Assembled with a mid-engine system \n" +
                        "Cutting-edge TTFAR technology",
                "Rs. 459,950",
                false
        ));
        list.add(new BikeModel(
                "Yadea T9",
                R.drawable.electric_yadeat9,
                "Subtle Front Face With Sleek Lines.\n" +
                        "Hydraulic Shock Absorber\n" +
                        "Cutting-edge TTFAR technology\n" +
                        "18 Mosfets 40A controller",
                "Rs. 599,950",
                false
        ));
        list.add(new BikeModel(
                "Yadea EZEEGO",
                R.drawable.electric_yadeaezeego,
                "Subtle Front Face With Sleek Lines.\n" +
                        "DC Brushless Motor\n" +
                        "9 Degrees Climbing Capacity\n" +
                        "LED Multi Colour Display",
                "Rs. 639,950",
                false
        ));
        list.add(new BikeModel(
                "Yadea E8S",
                R.drawable.electric_yadeae8s,
                "2000W high power motor\n" +
                        "Ride 100km+ on a single full charge\n" +
                        "72V38Ah Graphene battery\n" +
                        "Front & rear disc brakes",
                "Rs. 664,950",
                false
        ));
        list.add(new BikeModel(
                "Yadea EPOC",
                R.drawable.electric_yadeaepoc,
                "2000W high power motor\n" +
                        "Ride 100km+ on a single full charge\n" +
                        "72V38Ah Graphene battery\n" +
                        "Front & rear disc brakes",
                "Rs. 664,950",
                false
        ));
        list.add(new BikeModel(
                "TAILG BRAVO",
                R.drawable.electric_tailgbravo,
                "Motor: 1000W / Battery: 72V20Ah，Lead Acid\n" +
                        "Charging Time: 6-8h / Max Speed: 51km/h\n" +
                        "Max Range: 70km / Vehicle Size: 1720*730*1150mm\n" +
                        "Wheelbase: 1220mm / Brake: Disc/Disc",
                "Rs. 477,900",
                false
        ));
        list.add(new BikeModel(
                "TAILG A5",
                R.drawable.electric_tailga5,
                "Motor: 1200W / Battery: 72V32Ah，Lead acid\n" +
                        "Charging Time: 6-8h / Max Speed: 51KM/h\n" +
                        "Max Range: 120Km / Vehicle Size: 1810*680*1130mm\n" +
                        "Wheelbase: 1330mm / Brake: Disc/Disc",
                "Rs. 765,000",
                false
        ));
        list.add(new BikeModel(
                "TAILG LEDO",
                R.drawable.electric_tailgledo,
                "Motor: 400W / Battery: 48V12AH Lead Acid\n" +
                        "Charging Time: 4-6h / Max Speed: 25km/h\n" +
                        "Max Range: 40km / Vehicle Size: 1495*610*1000mm\n" +
                        "Wheelbase: 1060mm / Paddle: Yes",
                "Rs. 300,900",
                false
        ));
        list.add(new BikeModel(
                "TAILG YUTU",
                R.drawable.electric_tailglyutu,
                "Motor: 350W / Battery: 48V20AH Lead Acid\n" +
                        "Charging Time: 6-8h / Max Speed: 34km/h\n" +
                        "Max Range: 70km / Vehicle Size: 1650*770*1045mm\n" +
                        "Wheelbase: 1155mm / Paddle: Yes",
                "Rs. 422,440",
                false
        ));
        list.add(new BikeModel(
                "TAILG PACESETTER",
                R.drawable.electric_tailagpacesetter,
                "Motor: 350W / Battery: 48V20AH Lead Acid\n" +
                        "Charging Time: 6-8h / Max Speed: 34km/h\n" +
                        "Max Range: 70km / Vehicle Size: 1735*728*1103mm\n" +
                        "Wheelbase: 1245mm / Paddle: Yes",
                "Rs. 410,640",
                false
        ));
        list.add(new BikeModel(
                "TAILG HTX",
                R.drawable.electric_tilaghtx,
                "Motor: 2000W / Battery: 72V20AH Lead Acid\n" +
                        "Charging Time: 6-8h / Max Speed: 50km/h\n" +
                        "Max Range: 50km / Vehicle Size: 1955*680*1112mm\n" +
                        "Wheelbase: 1300mm / Brake: Disc/Disc",
                "Rs. 807,120",
                false
        ));
        list.add(new BikeModel(
                "TAILG TANK",
                R.drawable.electric_tilagtank,
                "Motor: 2000W / Battery: 72V23Ah，Lead acid\n" +
                        "Charging Time: 6-8h / Max Speed: 45Km/h\n" +
                        "Max Range: 60Km / Vehicle Size: 1945*750*1160mm\n" +
                        "Wheelbase: 1380mm / Brake: Disc/Drum",
                "Rs. 566,400",
                false
        ));
        return list;
    }
}