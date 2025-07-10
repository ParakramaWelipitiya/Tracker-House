package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    ImageButton btnPhone, btnEmail, btnFacebook, btnWhatsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Menu button → opens MenuActivity
        ImageButton menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(AboutActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        // Initialize buttons
        btnPhone = findViewById(R.id.btn_phone);
        btnEmail = findViewById(R.id.btn_email);
        btnFacebook = findViewById(R.id.btn_facebook);
        btnWhatsApp = findViewById(R.id.btn_whatsapp);

        // Phone button click
        btnPhone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0754357288"));
            startActivity(intent);
        });

        // Email button click
        btnEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:parakramawelipitiya00@gmail.com"));
            startActivity(Intent.createChooser(intent, "Send Email"));
        });

        // Facebook button click
        btnFacebook.setOnClickListener(v -> {
            String facebookUrl = "https://www.facebook.com/share/1ACrx9b7xu/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
            startActivity(intent);
        });

        // WhatsApp button click
        btnWhatsApp.setOnClickListener(v -> {
            String phoneNumber = "+94754357288";
            String message = "Hello Tracker House, I'm interested in your bikes.";

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage("com.whatsapp");
                String url = "https://wa.me/" + phoneNumber.replace("+", "") + "?text=" + Uri.encode(message);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
