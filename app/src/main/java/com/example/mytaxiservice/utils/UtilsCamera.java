package com.example.mytaxiservice.utils;



import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class UtilsCamera {
    private AppCompatActivity activity;
    private File photoFile;
    private Uri photoUri;

    public UtilsCamera(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void dispatchTakePictureIntent(ActivityResultLauncher<Intent> activityResultLauncher) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
                photoUri = FileProvider.getUriForFile(activity,
                        activity.getApplicationContext().getPackageName() + ".fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                activityResultLauncher.launch(takePictureIntent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File createImageFile() throws IOException {
        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = activity.getExternalFilesDir(null);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    public Uri getPhotoUri() {
        return photoUri;
    }
}
//usee in activity

// <provider
//android:name="androidx.core.content.FileProvider"
//android:authorities="${applicationId}.fileprovider"
//android:exported="false"
//android:grantUriPermissions="true">
//        <meta-data
//android:name="android.support.FILE_PROVIDER_PATHS"
//android:resource="@xml/file_paths" />
//    </provider>

//<paths>
//    <external-path name="external_files" path="."/>
//</paths>



///usee activity
//
//package com.example.taxiservice;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.taxiservice.utils.UtilsCamera;
//
//public class MainActivity extends AppCompatActivity {
//    private UtilsCamera utilsCamera;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        utilsCamera = new UtilsCamera(this);
//
//        // Register the ActivityResultLauncher
//        ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if (result.getResultCode() == RESULT_OK) {
//                        // Handle the image
//                        Uri imageUri = utilsCamera.getPhotoUri();
//                        // Use imageUri to display the image or perform other operations
//                    }
//                });
//
//        // Call this method to open the camera
//        utilsCamera.dispatchTakePictureIntent(takePictureLauncher);
//    }
//}
