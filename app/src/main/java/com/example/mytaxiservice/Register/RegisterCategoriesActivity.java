package com.example.mytaxiservice.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytaxiservice.R;
import com.example.mytaxiservice.databinding.ActivityRegisterCategoryBinding;


public class RegisterCategoriesActivity extends AppCompatActivity {

   // nullable|required_if:role,user|in:private,professional,disabled,student
    ActivityRegisterCategoryBinding binding;
    public static String type="professional";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegisterCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        binding.layoutPrivateac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="private";
                showToast("private");
            }
        });

      binding.layoutStudent.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("student");
            }

        });

        binding. layoutBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("professional");
            }
        });

        binding.layoutDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("disabled");
            }
        });
    }

    // Method to show Toast messages
    private void showToast(String message) {
        type=message;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getBaseContext(), RegisterAccountActivity.class);
        startActivity(i);
    }
}




