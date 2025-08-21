package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FaqActivity extends AppCompatActivity {

    TextView question1, answer1;
    TextView question2, answer2;
    TextView question3, answer3;
    TextView question4, answer4;
    TextView question5, answer5;
    TextView question6, answer6;
    TextView question7, answer7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        //menu button
        ImageButton menuBtn = findViewById(R.id.menuButton);
        menuBtn.setOnClickListener(v -> finish());

        //help button
        Button helpButton = findViewById(R.id.button4);
        helpButton.setOnClickListener(v -> {
            //navigate to aboutus
            Intent intent = new Intent(FaqActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        //link all questions and answers
        question1 = findViewById(R.id.question1);
        answer1 = findViewById(R.id.answer1);
        question2 = findViewById(R.id.question2);
        answer2 = findViewById(R.id.answer2);
        question3 = findViewById(R.id.question3);
        answer3 = findViewById(R.id.answer3);
        question4 = findViewById(R.id.question4);
        answer4 = findViewById(R.id.answer4);
        question5 = findViewById(R.id.question5);
        answer5 = findViewById(R.id.answer5);
        question6 = findViewById(R.id.question6);
        answer6 = findViewById(R.id.answer6);
        question7 = findViewById(R.id.question7);
        answer7 = findViewById(R.id.answer7);

        //toggle answer visibility on question tap
        setToggleListener(question1, answer1);
        setToggleListener(question2, answer2);
        setToggleListener(question3, answer3);
        setToggleListener(question4, answer4);
        setToggleListener(question5, answer5);
        setToggleListener(question6, answer6);
        setToggleListener(question7, answer7);
    }

    //FAQ questions and answers logic
    private void setToggleListener(TextView question, TextView answer) {
        question.setOnClickListener(v -> {
            if (answer.getVisibility() == View.GONE) {
                answer.setVisibility(View.VISIBLE);
            } else {
                answer.setVisibility(View.GONE);
            }
        });
    }
}
