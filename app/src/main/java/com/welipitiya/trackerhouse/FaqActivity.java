package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FaqActivity extends AppCompatActivity {

    // Flags to track if each answer is shown or hidden
    private boolean showAnswer1 = false;
    private boolean showAnswer2 = false;
    private boolean showAnswer3 = false;
    private boolean showAnswer4 = false;
    private boolean showAnswer5 = false;
    private boolean showAnswer6 = false;
    private boolean showAnswer7 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);  // Set layout XML for FAQ screen

        // Find all question and answer TextViews by their IDs
        TextView question1 = findViewById(R.id.question1);
        TextView answer1 = findViewById(R.id.answer1);
        TextView question2 = findViewById(R.id.question2);
        TextView answer2 = findViewById(R.id.answer2);
        TextView question3 = findViewById(R.id.question3);
        TextView answer3 = findViewById(R.id.answer3);
        TextView question4 = findViewById(R.id.question4);
        TextView answer4 = findViewById(R.id.answer4);
        TextView question5 = findViewById(R.id.question5);
        TextView answer5 = findViewById(R.id.answer5);
        TextView question6 = findViewById(R.id.question6);
        TextView answer6 = findViewById(R.id.answer6);
        TextView question7 = findViewById(R.id.question7);
        TextView answer7 = findViewById(R.id.answer7);

        // When a question is clicked, toggle its answer's visibility
        question1.setOnClickListener(v -> {
            showAnswer1 = !showAnswer1;  // Flip boolean
            answer1.setVisibility(showAnswer1 ? View.VISIBLE : View.GONE);  // Show or hide answer
        });

        question2.setOnClickListener(v -> {
            showAnswer2 = !showAnswer2;
            answer2.setVisibility(showAnswer2 ? View.VISIBLE : View.GONE);
        });

        question3.setOnClickListener(v -> {
            showAnswer3 = !showAnswer3;
            answer3.setVisibility(showAnswer3 ? View.VISIBLE : View.GONE);
        });

        question4.setOnClickListener(v -> {
            showAnswer4 = !showAnswer4;
            answer4.setVisibility(showAnswer4 ? View.VISIBLE : View.GONE);
        });

        question5.setOnClickListener(v -> {
            showAnswer5 = !showAnswer5;
            answer5.setVisibility(showAnswer5 ? View.VISIBLE : View.GONE);
        });

        question6.setOnClickListener(v -> {
            showAnswer6 = !showAnswer6;
            answer6.setVisibility(showAnswer6 ? View.VISIBLE : View.GONE);
        });

        question7.setOnClickListener(v -> {
            showAnswer7 = !showAnswer7;
            answer7.setVisibility(showAnswer7 ? View.VISIBLE : View.GONE);
        });

        // Menu button to open HomeMenuActivity
        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(FaqActivity.this, HomeMenuActivity.class);
            startActivity(intent);
        });

        // Contact button to open AboutUsActivity
        Button contactButton = findViewById(R.id.button4);
        contactButton.setOnClickListener(v -> {
            Intent intent = new Intent(FaqActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
    }
}
