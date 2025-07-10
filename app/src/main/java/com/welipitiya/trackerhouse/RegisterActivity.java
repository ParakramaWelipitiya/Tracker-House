package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    // Declare UI elements
    EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    Button registerButton;
    TextView loginText;
    UserDatabase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize view references
        usernameEditText = findViewById(R.id.Username_input);
        emailEditText = findViewById(R.id.email_input);
        passwordEditText = findViewById(R.id.password_input);
        confirmPasswordEditText = findViewById(R.id.confirmpassword_input);
        registerButton = findViewById(R.id.register_btn);
        loginText = findViewById(R.id.login_text);

        // Initialize database helper
        dbHelper = new UserDatabase(this);

        // Register button click listener
        registerButton.setOnClickListener(v -> {
            // Get user input
            String username = usernameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            // Validate input fields
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if user already exists
            if (dbHelper.checkUserExists(email)) {
                Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
            } else {
                // Insert user into database
                boolean inserted = dbHelper.insertUser(email, username, password);
                if (inserted) {
                    // Save user data in SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("UserData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", username);
                    editor.putString("email", email);
                    editor.apply();

                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                    // Redirect to LoginActivity
                    startActivity(new Intent(this, LoginActivity.class));
                    finish(); // Finish this activity
                } else {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Navigate to LoginActivity when login text clicked
        loginText.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}