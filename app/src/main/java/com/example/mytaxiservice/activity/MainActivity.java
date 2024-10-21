package com.example.mytaxiservice.activity;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mytaxiservice.R;
import com.example.mytaxiservice.fragment.FirstFragment;
import com.example.mytaxiservice.fragment.FourthFragment;
import com.example.mytaxiservice.fragment.SecondFragment;
import com.example.mytaxiservice.fragment.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Load the default fragment
        loadFragment(new FirstFragment());

        // Set up listener for BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                selectedFragment = new FirstFragment();
            } else if (itemId == R.id.account) {
                selectedFragment = new SecondFragment();
            } else if (itemId == R.id.activities) {
                selectedFragment = new ThirdFragment();
            } else if (itemId == R.id.services) {
                selectedFragment = new FourthFragment();
            }

            return loadFragment(selectedFragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        // Replace the fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof FirstFragment) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity(); // This closes the app
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            super.onBackPressed(); // Handle back press for other fragments
        }
    }

}
