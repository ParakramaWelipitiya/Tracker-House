package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        //menu button
        ImageButton menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(PrivacyActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        WebView webView = findViewById(R.id.webViewPrivacy);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.loadUrl("file:///android_asset/privacy.html");
    }
}