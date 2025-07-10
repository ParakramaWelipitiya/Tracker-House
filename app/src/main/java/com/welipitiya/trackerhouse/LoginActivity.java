package com.welipitiya.trackerhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {

    // UI components
    EditText emailEditText, passwordEditText;
    Button loginButton;
    TextView registerText;

    // Database helper for login validation
    UserDatabase dbHelper;

    // Biometric components
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Enable edge-to-edge layout
        EdgeToEdge.enable(this);

        // Apply padding to avoid overlap with system UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind UI elements
        emailEditText = findViewById(R.id.email_input);
        passwordEditText = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_btn);
        registerText = findViewById(R.id.register_text);

        dbHelper = new UserDatabase(this); // Initialize database helper

        // Setup biometric authentication
        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {

            // Called when there's an error with the authentication process
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            // Called when biometric authentication is successful
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();

                // Navigate to Dashboard if authentication is successful
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();
            }

            // Called when biometric authentication fails
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        // Configure the biometric prompt dialog
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login with fingerprint")
                .setSubtitle("Use your fingerprint to login")
                .setNegativeButtonText("Use Email and Password")
                .build();

        // Check if biometric authentication is available
        BiometricManager biometricManager = BiometricManager.from(this);

        if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
            // Use biometric login if available
            loginButton.setOnClickListener(v -> biometricPrompt.authenticate(promptInfo));
        } else {
            // If biometric is unavailable, fallback to email/password login
            loginButton.setOnClickListener(v -> {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate input fields
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check credentials in the database
                if (dbHelper.validateUser(email, password)) {
                    // Fetch username
                    String username = dbHelper.getUsernameByEmail(email);

                    // Store user session using SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("UserData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", username); // Save username
                    editor.putString("email", email);       // Save email (optional)
                    editor.apply();

                    Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                    // Navigate to dashboard
                    startActivity(new Intent(this, DashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Navigate to registration screen when "register" text is clicked
        registerText.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
