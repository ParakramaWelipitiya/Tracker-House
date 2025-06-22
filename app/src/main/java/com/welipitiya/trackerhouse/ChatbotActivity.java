package com.welipitiya.trackerhouse;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatbotActivity extends AppCompatActivity {

    private LinearLayout chatLayout, suggestionLayout;  // Layout for chat bubbles and suggestions
    private ScrollView scrollView;                       // Scroll for chat messages
    private EditText inputMessage;                       // Input text box for user messages
    private ImageButton sendButton;                      // Send button

    // Predefined questions and answers pairs for chatbot
    private final String[][] qaPairs = {
            {"hi", "Hello! How can I help you ?"},
            {"Show me available bikes", "We have D tracker 2018, NS200 Pulsar, and WRX Yamaha available."},
            {"Where is the Dealership?", "Dealership is located in Gampaha.You can check it on Location Tab"},
            {"What are the working hours?", "We are open Monday to Saturday, 9 AM to 6 PM."},
            {"How can I test ride a bike?", "You can book a test ride through contacting us."}
    };

    // Suggested questions buttons below chat for easy click
    private final String[] suggestions = {
            "Show me available bikes",
            "Where is the Dealership?",
            "What are the working hours?",
            "How can I test ride a bike?"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);  // Connect layout XML

        // Find views by id
        chatLayout = findViewById(R.id.chatLayout);
        scrollView = findViewById(R.id.chatScrollView);
        inputMessage = findViewById(R.id.inputMessage);
        sendButton = findViewById(R.id.sendButton);

        // Create suggestion layout dynamically and add it to chat layout
        suggestionLayout = new LinearLayout(this);
        suggestionLayout.setOrientation(LinearLayout.VERTICAL);
        chatLayout.addView(suggestionLayout);

        // Send button click event
        sendButton.setOnClickListener(v -> {
            String message = inputMessage.getText().toString().trim();  // Get user message
            if (!TextUtils.isEmpty(message)) {                          // Check message is not empty
                addMessageBubble(message, true);                        // Show user message bubble
                inputMessage.setText("");                               // Clear input box
                String reply = getBotReply(message);                    // Get chatbot reply
                addMessageBubble(reply, false);                         // Show bot reply bubble
                addSuggestions();                                        // Show suggestion buttons again
            }
        });

        // Show welcome message from bot when activity starts
        addMessageBubble("Hi there! I'm your assistant. Ask me anything.", false);

        // Show suggestion buttons initially
        addSuggestions();

        ImageButton closeButton = findViewById(R.id.closeButton);
        // Close button click event - go back to previous screen
        closeButton.setOnClickListener(v -> finish());
    }

    // Get bot reply based on user message
    private String getBotReply(String userMsg) {
        for (String[] pair : qaPairs) {
            if (pair[0].equalsIgnoreCase(userMsg)) {
                return pair[1];  // Return matching answer
            }
        }
        // Default reply if not understood
        return "Sorry, I didn’t understand that. Please try a different question.";
    }

    // Add a chat bubble message to chatLayout
    private void addMessageBubble(String message, boolean isUser) {
        TextView bubble = new TextView(this);
        bubble.setText(message);
        bubble.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        bubble.setTypeface(Typeface.SANS_SERIF);
        bubble.setPadding(30, 20, 30, 20);
        bubble.setMaxWidth((int) (getResources().getDisplayMetrics().widthPixels * 0.75));  // Max width 75% of screen

        // Background shape with rounded corners and color based on sender
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(40);
        bg.setColor(isUser ? 0xFF006AFF : 0xFFE8EAF6);       // Blue for user, light gray for bot
        bubble.setTextColor(isUser ? 0xFFFFFFFF : 0xFF000000);  // White text user, black text bot
        bubble.setBackground(bg);

        // Layout params for positioning bubble left or right
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = isUser ? Gravity.END : Gravity.START;  // User right, bot left
        params.setMargins(20, 16, 20, 0);
        bubble.setLayoutParams(params);

        chatLayout.addView(bubble);  // Add bubble to chat layout
        scrollToBottom();             // Scroll chat to bottom
    }

    // Add suggestion buttons below chat
    private void addSuggestions() {
        suggestionLayout.removeAllViews();  // Clear old buttons

        for (String suggestion : suggestions) {
            Button btn = new Button(this);
            btn.setText(suggestion);
            btn.setTextColor(0xFF006AFF);      // Blue text color
            btn.setTextSize(14);
            btn.setAllCaps(false);             // Normal case text
            btn.setPadding(20, 10, 20, 10);
            btn.setBackgroundColor(0xFFF0F8FF);  // Light blue background

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(16, 10, 16, 10);
            btn.setLayoutParams(params);

            // When suggestion button clicked, add user message and bot reply
            btn.setOnClickListener(v -> {
                addMessageBubble(suggestion, true);          // User bubble
                String reply = getBotReply(suggestion);      // Bot reply
                addMessageBubble(reply, false);               // Bot bubble
                addSuggestions();                             // Refresh suggestions
            });

            suggestionLayout.addView(btn);  // Add button to suggestions layout
        }

        scrollToBottom();  // Scroll chat to bottom after adding suggestions
    }

    // Scroll chat ScrollView to bottom
    private void scrollToBottom() {
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }
}