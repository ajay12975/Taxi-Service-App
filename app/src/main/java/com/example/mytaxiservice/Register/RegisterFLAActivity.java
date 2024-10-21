package com.example.mytaxiservice.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mytaxiservice.R;
import com.example.mytaxiservice.databinding.ActivityRegisterAccountBinding;
import com.example.mytaxiservice.databinding.ActivityRegisterFlaactivityBinding;
import com.example.mytaxiservice.login.LoginActivity;

public class RegisterFLAActivity extends AppCompatActivity {

    public  static String firstname="";
    public  static String lastname="";

    ActivityRegisterFlaactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterFlaactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterFLAActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // Set click listener for the register button
        binding.btnloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndRegister();
                Intent intent= new Intent(RegisterFLAActivity.this,RegisterCategoriesActivity.class);
                startActivity(intent);
            }
        });
    }

    // Validation function
    private void validateAndRegister() {


        // Check if fields are empty
        if (binding.edfirstname.getText().toString().isEmpty()) {
            binding.edfirstname.setError("First name is required");
            binding.edfirstname.requestFocus();
            return;
        }

        if (binding.edlastname.getText().toString().isEmpty()) {
            binding.edlastname.setError("First name is required");
            binding.edlastname.requestFocus();
            return;
        }

        callApi();
        // If validation passes, show success message (You can perform the next action here)
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
    }

    private void callApi() {
        firstname=binding.edfirstname.getText().toString().trim();
        lastname=binding.edlastname.getText().toString().trim();
        Intent i = new Intent(getBaseContext(), RegisterCategoriesActivity.class);
        startActivity(i);
    }
}
