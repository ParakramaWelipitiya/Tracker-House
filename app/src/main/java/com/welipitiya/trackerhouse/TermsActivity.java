package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TermsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        //menu button
        ImageButton menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(TermsActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        //webview for terms and privacy
        WebView webView = findViewById(R.id.webViewTerms);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.loadUrl("file:///android_asset/terms.html");
    }
}