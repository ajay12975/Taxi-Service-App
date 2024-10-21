package com.example.mytaxiservice.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mytaxiservice.R;
import com.example.mytaxiservice.activity.MainActivity;
import com.example.mytaxiservice.login.LoginActivity;

public class SplaceScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splace_screen);

        // Handling window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Delay for splash screen (3 seconds)
        new Handler().postDelayed(() -> {
            // After the delay, start the main activity
           // Intent intent = new Intent(SplaceScreenActivity.this, LoginActivity.class);
            Intent intent = new Intent(SplaceScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finish the splash activity so it's removed from the back stack
        }, 3000); // 3 second delay (3000 milliseconds)
    }
}
