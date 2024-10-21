package com.example.mytaxiservice.utils;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.content.FileProvider;

import java.io.File;

public class UtilCameraGallery {

    private static Uri imageUri;

    public static void showImagePickerDialog(Context context, ActivityResultLauncher<Intent> cameraLauncher, ActivityResultLauncher<Intent> galleryLauncher) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Image Source");
        builder.setItems(new CharSequence[]{"Camera", "Gallery"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // Camera option
                    openCamera(context, cameraLauncher);
                } else {
                    // Gallery option
                    openGallery(galleryLauncher);
                }
            }
        });
        builder.show();
    }

    // Method to open Camera
    private static void openCamera(Context context, ActivityResultLauncher<Intent> cameraLauncher) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageUri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (imageUri != null) {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraLauncher.launch(cameraIntent);
        } else {
            Toast.makeText(context, "Camera error", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to open Gallery
    private static void openGallery(ActivityResultLauncher<Intent> galleryLauncher) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }

    // Retrieve the image URI (from the camera)
    public static Uri getImageUri() {
        return imageUri;
    }

    public static String getRealPathFromURI(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                result = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return result;
    }

}
