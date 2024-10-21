package com.example.mytaxiservice.categories;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mytaxiservice.sharedPreference.SharedPreference;
import com.example.mytaxiservice.databinding.ActivityCategoriesBinding;
import com.example.mytaxiservice.server.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends AppCompatActivity {

    ActivityCategoriesBinding activityCategoriesBinding;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        activityCategoriesBinding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(activityCategoriesBinding.getRoot());

        sharedPreference = new SharedPreference(this);

        Log.i("TOKENN", sharedPreference.getString("token").toString().trim());

        callApi();
    }

    private void callApi() {
        RetrofitClient.getApiService().categoriesData("Bearer " + sharedPreference.getString("token").toString().trim()).enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.code() == 200) {

                    Toast.makeText(CategoriesActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    CategoryAdapter categoryAdapter = new CategoryAdapter(response.body().getData());
                    activityCategoriesBinding.rvcategoryRecy.setAdapter(categoryAdapter);


//                    Intent i = new Intent(getBaseContext(), MainActivity.class);
//                    startActivity(i);

                } else {
                    Log.i("ERRORTAG", "SUCCESS RESPONE" + response.code());
                    Toast.makeText(CategoriesActivity.this, "Response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                Toast.makeText(CategoriesActivity.this, "Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}