package com.example.mytaxiservice.Register;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytaxiservice.activity.MainActivity;
import com.example.mytaxiservice.databinding.ActivityRegisterAccountBinding;
import com.example.mytaxiservice.fragment.FirstFragment;
import com.example.mytaxiservice.server.RetrofitClient;
import com.example.mytaxiservice.utils.UtilCalender;
import com.example.mytaxiservice.utils.UtilCameraGallery;
import com.example.mytaxiservice.utils.UtilSpinner;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAccountActivity extends AppCompatActivity {

    // Declare result launchers for Camera and Gallery
    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

    String image;
    ActivityRegisterAccountBinding bindind;
    List<String> items = Arrays.asList("Select Gender", "male", "female","other");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindind = ActivityRegisterAccountBinding.inflate(getLayoutInflater());
        setContentView(bindind.getRoot());
        Log.i("ResponseRegistor","Response UserType :" +RegisterCategoriesActivity.type);
        Log.i("ResponseRegistor","Response name :" +RegisterFLAActivity.firstname);
        Log.i("ResponseRegistor","Response lastname :" +RegisterFLAActivity.lastname);
        spinnerView();

        cameraGalleryView();
        initImageResultLaunchers();

        calenderView();

        bindind.tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bindind.imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Set a click listener for the Finish button
        bindind.btnloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform validation and submit the form
                if (validateInput()) {

                    Intent intent=new Intent(RegisterAccountActivity.this, MainActivity.class);
                    startActivity(intent);
                    callApi();

                }
            }
        });

    }

    private void callApi() {
        // Prepare the data for the API request
        RequestBody name = RequestBody.create(MultipartBody.FORM, RegisterFLAActivity.firstname + " " + RegisterFLAActivity.lastname);
        RequestBody email = RequestBody.create(MultipartBody.FORM, bindind.tvEmail.getText().toString().trim());
        RequestBody mobile = RequestBody.create(MultipartBody.FORM, bindind.tvPhonenumber.getText().toString().trim());
        RequestBody password = RequestBody.create(MultipartBody.FORM, bindind.tvPassword.getText().toString().trim());
        RequestBody status = RequestBody.create(MultipartBody.FORM, "1");
        RequestBody role = RequestBody.create(MultipartBody.FORM, "user");
        RequestBody userType = RequestBody.create(MultipartBody.FORM, RegisterCategoriesActivity.type);
        RequestBody companyName = RequestBody.create(MultipartBody.FORM, "Test company");
        RequestBody countryCode = RequestBody.create(MultipartBody.FORM, "+91");
        RequestBody deviceType = RequestBody.create(MultipartBody.FORM, "android");

        MultipartBody.Part profileImg = null;

        // Check if the image URI is not empty
        if (image != null && !image.isEmpty()) {
            Uri imageUri = Uri.parse(image);
            File imageFile = new File(UtilCameraGallery.getRealPathFromURI(this, imageUri)); // Convert URI to file path
            RequestBody requestFile = RequestBody.create(MultipartBody.FORM, imageFile);
            profileImg = MultipartBody.Part.createFormData("profile_img", imageFile.getName(), requestFile);
        }

        // Call the API using Retrofit
        RetrofitClient.getApiService().storeData(name, email, mobile, password, status, role, userType, companyName, countryCode, deviceType, profileImg).enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("ResponseRegistor","Response Success :" +response.body().getMessage());
                    Toast.makeText(RegisterAccountActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterAccountActivity.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                    Log.i("ResponseRegistor","Response Failure :" +response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                Toast.makeText(RegisterAccountActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.i("ResponseRegistor","OnFailure :" +t.getMessage());
            }
        });
    }

    private void calenderView() {

        bindind.tvCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilCalender.showDatePickerWithTextView(RegisterAccountActivity.this,bindind.tvCalendar,"dd/mm/yyyy");
            }
        });

    }

    private void spinnerView() {
        UtilSpinner.setUpSpinner(RegisterAccountActivity.this,bindind.tvGender,items);
    }

    // Validate the user input
    private boolean validateInput() {

        String email = bindind.tvEmail.getText().toString().trim();
        String gender =bindind. tvGender.getSelectedItem().toString();

        // Check if any field is empty
        if (TextUtils.isEmpty(bindind.tvEmail.getText().toString().trim())) {
            bindind.tvEmail.setError("Email is required");
            return false;
        }

        if (TextUtils.isEmpty(bindind.tvPhonenumber.getText().toString().trim())) {
            bindind.tvPhonenumber.setError("Phone number is required");
            return false;
        }

        if (TextUtils.isEmpty(bindind.tvPassword.getText().toString().trim())) {
            bindind.tvPassword.setError("Password is required");
            return false;
        }
        if (!email.matches(emailPattern)) {
            bindind.tvEmail.setError("Please enter a valid email address");
            return false;
        }

        if (TextUtils.isEmpty(bindind.tvConfirmPassword.getText().toString().trim())) {
            bindind.tvConfirmPassword.setError("Confirm Password is required");
            return false;
        }

        if (!bindind.tvPassword.getText().toString().trim().equals(bindind.tvConfirmPassword.getText().toString().trim())) {
            bindind.tvConfirmPassword.setError("Passwords do not match");
            return false;
        }

        if (TextUtils.isEmpty(bindind.tvCalendar.getText().toString().trim())) {
            bindind.tvCalendar.setError("Calendar date is required");
            return false;
        }

        if (gender.equals("Select Gender")) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!bindind.loginText.isChecked()){
            Toast.makeText(getBaseContext(), "Accept term and condition", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true; // Return true if all validations pass
    }

    // Initialize the result launchers for camera and gallery
    private void initImageResultLaunchers() {
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Uri imageUri = UtilCameraGallery.getImageUri(); // Get image URI from camera
                        bindind.imgprofile.setImageURI(imageUri); // Set image to ImageView
                        uploadImageToServer(imageUri); // Upload image to the server
                    }
                });

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        bindind.imgprofile.setImageURI(selectedImageUri); // Set image to ImageView
                        uploadImageToServer(selectedImageUri); // Upload image to the server
                    }
                });
    }

    // Handle camera and gallery selection for image upload
    private void cameraGalleryView() {
        bindind.imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show dialog to pick Camera or Gallery
                UtilCameraGallery.showImagePickerDialog(RegisterAccountActivity.this, cameraLauncher, galleryLauncher);
            }
        });
    }

    private void uploadImageToServer(Uri imageUri) {
        // Add your server upload logic here

        image=imageUri.toString();
        // Example: Using Retrofit to upload the image file using its URI
        Toast.makeText(this, "Image uploaded: " + imageUri.toString(), Toast.LENGTH_SHORT).show();
        // Convert Uri to file and upload to server
        // Use your API call here to upload the image
    }
}
