package com.welipitiya.trackerhouse;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Arrays;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private final LatLng trackerHouse = new LatLng(7.0847, 79.9930);
    private final float arrivalRadius = 100f;
    private boolean hasArrived = false;
    private boolean cameraMoved = false;

    private Marker userMarker;
    private Marker trackerMarker;
    private Polyline routePolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        ImageButton menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(MapsActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) return;

                for (Location location : locationResult.getLocations()) {
                    LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    Log.d("LOCATION", "Lat: " + userLatLng.latitude + ", Lng: " + userLatLng.longitude);

                    if (mMap != null) {
                        if (userMarker == null) {
                            userMarker = mMap.addMarker(new MarkerOptions()
                                    .position(userLatLng)
                                    .title("You"));
                        } else {
                            userMarker.setPosition(userLatLng);
                        }

                        if (trackerMarker == null) {
                            trackerMarker = mMap.addMarker(new MarkerOptions()
                                    .position(trackerHouse)
                                    .title("Tracker House"));

                            mMap.addCircle(new CircleOptions()
                                    .center(trackerHouse)
                                    .radius(arrivalRadius)
                                    .strokeColor(Color.BLUE)
                                    .fillColor(0x220000FF)
                                    .strokeWidth(3));
                        }

                        if (routePolyline == null) {
                            routePolyline = mMap.addPolyline(new PolylineOptions()
                                    .add(userLatLng, trackerHouse)
                                    .width(5)
                                    .color(Color.parseColor("#4285F4")));
                        } else {
                            routePolyline.setPoints(Arrays.asList(userLatLng, trackerHouse));
                        }

                        if (!cameraMoved) {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15));
                            cameraMoved = true;
                        }

                        float[] distance = new float[1];
                        Location.distanceBetween(
                                userLatLng.latitude, userLatLng.longitude,
                                trackerHouse.latitude, trackerHouse.longitude,
                                distance
                        );

                        if (distance[0] <= arrivalRadius && !hasArrived) {
                            hasArrived = true;
                            Toast.makeText(MapsActivity.this, "You have arrived at Tracker House!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            startLocationUpdates();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
                .setMinUpdateIntervalMillis(5000)
                .build();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMap != null &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                startLocationUpdates();
            }
        } else {
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}
