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

    // Tracker House location
    private final LatLng trackerHouse = new LatLng(7.0847, 79.9930); // Gampaha

    private final float arrivalRadius = 100f; // Arrival radius in meters
    private boolean hasArrived = false;

    private Marker userMarker;
    private Marker trackerMarker;
    private Polyline routePolyline;  // Holds the route polyline

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Initialize FusedLocationProviderClient to fetch device's location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Load the Google Map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this); // Async call to load the map
        }

        // Menu button to return to HomeMenuActivity
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(MapsActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });

        // Location updates callback
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) return;

                for (Location location : locationResult.getLocations()) {
                    LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                    // Log current user location
                    Log.d("LOCATION", "Lat: " + location.getLatitude() + ", Lng: " + location.getLongitude());

                    if (mMap != null) {
                        // Add or update user marker
                        if (userMarker == null) {
                            userMarker = mMap.addMarker(new MarkerOptions()
                                    .position(userLatLng)
                                    .title("You are here"));
                        } else {
                            userMarker.setPosition(userLatLng);
                        }

                        // Add Tracker House marker and circle only once
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

                        // Draw or update red route line between user and Tracker House
                        if (routePolyline == null) {
                            routePolyline = mMap.addPolyline(new PolylineOptions()
                                    .add(userLatLng, trackerHouse)
                                    .width(5)
                                    .color(Color.RED));
                        } else {
                            routePolyline.setPoints(Arrays.asList(userLatLng, trackerHouse));
                        }

                        // Move camera to user location
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15));

                        // Calculate distance to Tracker House
                        float[] distance = new float[1];
                        Location.distanceBetween(
                                location.getLatitude(), location.getLongitude(),
                                trackerHouse.latitude, trackerHouse.longitude,
                                distance
                        );

                        // Notify user on arrival
                        if (distance[0] <= arrivalRadius && !hasArrived) {
                            hasArrived = true;
                            Toast.makeText(MapsActivity.this, "You have arrived at Tracker House!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        };
    }

    // Callback when map is ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Check location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true); // Show blue dot on map
            startLocationUpdates();
        } else {
            // Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    // Start requesting location updates
    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // every 10 seconds
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Ensure permission is granted before starting
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    // Stop location updates to save battery
    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    // Resume tracking when activity resumes
    @Override
    protected void onResume() {
        super.onResume();
        if (mMap != null &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        }
    }

    // Stop tracking when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    // Handle user response to location permission request
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
