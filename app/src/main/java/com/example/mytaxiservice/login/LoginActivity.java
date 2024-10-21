package com.example.mytaxiservice.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytaxiservice.Register.RegisterAccountActivity;
import com.example.mytaxiservice.Register.RegisterCategoriesActivity;
import com.example.mytaxiservice.Register.RegisterFLAActivity;
import com.example.mytaxiservice.activity.MainActivity;
import com.example.mytaxiservice.sharedPreference.SharedPreference;
import com.example.mytaxiservice.databinding.ActivityLoginBinding;
import com.example.mytaxiservice.fragment.FirstFragment;
import com.example.mytaxiservice.server.RetrofitClient;
import com.example.mytaxiservice.utils.UtilsPermissionManage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        sharedPreference =new SharedPreference(this);
        allpermission();

        activityLoginBinding.tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        activityLoginBinding.tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, RegisterFLAActivity.class);
                startActivity(intent);
            }
        });



        //depricated
//        EditText edtusername=findViewById(R.id.edtusername);
//
//        String username=edtusername.getText().toString();
//
//        if(username.isEmpty())
//        {
//            Toast.makeText(this, "enter user name", Toast.LENGTH_SHORT).show();
//        }
//
//        Button btnlogin=findViewById(R.id.btnloginbutton);
//
//        btnlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        //view binding in use

        activityLoginBinding.btnloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(activityLoginBinding.tvEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "enter user name", Toast.LENGTH_SHORT).show();
                    return;
                }if(activityLoginBinding.tvPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "enter user password", Toast.LENGTH_SHORT).show();
                    return;
                }if(!activityLoginBinding.checkbox.isChecked()){
                    Toast.makeText(getBaseContext(), "Accept Remember Me", Toast.LENGTH_SHORT).show();
                    return;
                }

                callApi();
            };
        });

    }

    private void callApi() {
        RetrofitClient.getApiService().loginData(activityLoginBinding.tvEmail.getText().toString().trim(),activityLoginBinding.tvPassword.getText().toString(),"user").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.code() == 200) {
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
                    sharedPreference.saveString("token",response.body().getData().getToken());
                    sharedPreference.setLogin(true);
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(LoginActivity.this, "Response failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Failure"+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void allpermission() {

        // Check if all permissions are granted
        if (!UtilsPermissionManage.arePermissionsGranted(this)) {
            // Request permissions if not granted
            UtilsPermissionManage.requestPermissions(this);
        } else {
            proceedWithAppLogic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UtilsPermissionManage.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check if any permissions were denied
        boolean allPermissionsGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (allPermissionsGranted) {
            proceedWithAppLogic();
        } else {
            Toast.makeText(this, "Some permissions were denied. Please enable them in settings.", Toast.LENGTH_LONG).show();
        }
    }

    // Call this method if permissions are already granted
    private void proceedWithAppLogic() {
        Toast.makeText(this, "All permissions are granted. Proceeding...", Toast.LENGTH_SHORT).show();
        // Add your logic that requires permissions here
    }

}