package com.example.mytaxiservice.tripAddress;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaxiservice.R;
import com.example.mytaxiservice.databinding.ActivityTripCategoryBinding;
import com.example.mytaxiservice.fragment.FirstFragment;
import com.example.mytaxiservice.server.RetrofitClient;
import com.example.mytaxiservice.sharedPreference.SharedPreference;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Trip_Category extends AppCompatActivity implements RecentAddressAdapter.OnAddressClickListener {

    private LinearLayout linearLayout;
    private TextView pickUP;
    private TextView dropLocation;
    private ImageView btnBack;
    private RecyclerView recyclerView;

    // Binding and SharedPreference instances
    private ActivityTripCategoryBinding activityTripCategoryBinding;
    private SharedPreference sharedPreference;

    private static final int AUTOCOMPLETE_REQUEST_CODE = 1; // Request code for the place picker
    private static final int AUTOCOMPLETE_REQUEST_CODE2 = 2; // Request code for the place picker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the ViewBinding
        activityTripCategoryBinding = ActivityTripCategoryBinding.inflate(getLayoutInflater());
        setContentView(activityTripCategoryBinding.getRoot());

        // Initialize UI components
        linearLayout = findViewById(R.id.lnrLayout);
        pickUP = findViewById(R.id.edtEnterLocation);
        dropLocation = findViewById(R.id.edtDropLocation);
        btnBack = findViewById(R.id.btnBack);
        recyclerView = findViewById(R.id.rvRecent);

        googleSearchAddress();

        // Fetch current and pickup locations from FirstFragment and set them in EditTexts
        activityTripCategoryBinding.edtEnterLocation.setText(FirstFragment.current);
        pickUP.setText(FirstFragment.pickup);

        // Handle back button click
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Return to the previous screen
            }
        });

        // Initialize SharedPreference and fetch token for API call
        sharedPreference = new SharedPreference(this);
        Log.i("TOKENN", sharedPreference.getString("token").toString().trim());

        // Set up click listener on pickup location to open Google Places Autocomplete
        pickUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAutocompleteActivity();
            }
        });
        dropLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAutocompleteActivity2();
            }
        });

        // Call API to fetch recent addresses and display them in RecyclerView
        callApi();
    }
    private void googleSearchAddress() {
        // Initialize Google Places API
        if (!Places.isInitialized()) {
            String apiKey = getString(R.string.YOUR_API_KEY); // Fetch the API key from strings.xml
            Places.initialize(getApplicationContext(), apiKey);
        }
    }

    /**
     * Opens the Google Places Autocomplete Activity when user clicks on the pickup Textview
     */
    private void openAutocompleteActivity() {
        // Set the fields you want to retrieve from the place search
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

        // Create an intent to start the Autocomplete activity
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE); // Start the autocomplete intent
    }

    private void openAutocompleteActivity2() {
        // Set the fields you want to retrieve from the place search
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

        // Create an intent to start the Autocomplete activity
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE2); // Start the autocomplete intent
    }

    /**
     * Handle the result returned from the Autocomplete activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("Google Place", "Place: " + place.getName() + ", " + place.getId());

                // Set the selected place's name to the pickup EditText
                pickUP.setText(place.getName());

                // Optionally, store the location data (LatLng) if needed
                if (place.getLatLng() != null) {
                    Log.i("Google Place", "LatLng: " + place.getLatLng().toString());
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // Handle the error if something went wrong
                Log.i("Google Place Error", Autocomplete.getStatusFromIntent(data).getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation
            }
        }
        else {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("Google Place", "Place: " + place.getName() + ", " + place.getId());

                // Set the selected place's name to the drop location EditText
                dropLocation.setText(place.getName());

                // Optionally, store the location data (LatLng) if needed
                if (place.getLatLng() != null) {
                    Log.i("Google Place", "LatLng: " + place.getLatLng().toString());
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // Handle the error if something went wrong
                Log.i("Google Place Error", Autocomplete.getStatusFromIntent(data).getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation
            }
        }
    }

    /**
     * Calls the API to fetch recent addresses using Retrofit.
     * The API response is then handled and the recent addresses are shown in a RecyclerView.
     */
    private void callApi() {
        // API call to fetch recent addresses
        RetrofitClient.getApiService()
                .fetchRecentAddress("Bearer " + sharedPreference.getString("token").toString().trim())
                .enqueue(new Callback<RecentAddressResponse>() {

                    @Override
                    public void onResponse(Call<RecentAddressResponse> call, Response<RecentAddressResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            // Get the list of recent addresses
                            List<RecentAddressResponse.Datum> addressList = response.body().getData();

                            // Set up RecyclerView with RecentAddressAdapter
                            RecentAddressAdapter adapter = new RecentAddressAdapter(addressList, Trip_Category.this);
                            recyclerView.setLayoutManager(new LinearLayoutManager(Trip_Category.this));
                            recyclerView.setAdapter(adapter);
                        } else {
                            // Handle response when there's no data or API fails
                            Log.e("API_ERROR", "Response unsuccessful or empty");
                        }
                    }

                    @Override
                    public void onFailure(Call<RecentAddressResponse> call, Throwable t) {
                        // Log the error or show a user-friendly message
                        Log.e("API_FAILURE", "API call failed: " + t.getMessage());
                    }
                });
    }

    @Override
    public void onAddressClick(String address) {
        // Set the clicked address to the dropLocation TextView
        dropLocation.setText(address);
    }
}
