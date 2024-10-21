package com.example.mytaxiservice.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class UtilsPermissionManage {

    private static final int PERMISSION_REQUEST_CODE = 100;

    // Define all the permissions your application may need
    public static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    public static final String FINE_LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String COARSE_LOCATION_PERMISSION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String READ_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String WRITE_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String POST_NOTIFICATIONS_PERMISSION = Manifest.permission.POST_NOTIFICATIONS; // For API 33+
    public static final String INTERNET_PERMISSION = Manifest.permission.INTERNET;
    public static final String ACCESS_NETWORK_STATE_PERMISSION = Manifest.permission.ACCESS_NETWORK_STATE;
    public static final String RECEIVE_BOOT_COMPLETED_PERMISSION = Manifest.permission.RECEIVE_BOOT_COMPLETED;
    public static final String VIBRATE_PERMISSION = Manifest.permission.VIBRATE;
    public static final String WAKE_LOCK_PERMISSION = Manifest.permission.WAKE_LOCK;
    public static final String FOREGROUND_SERVICE_PERMISSION = Manifest.permission.FOREGROUND_SERVICE;
    public static final String READ_CONTACTS_PERMISSION = Manifest.permission.READ_CONTACTS;
    public static final String WRITE_CONTACTS_PERMISSION = Manifest.permission.WRITE_CONTACTS;

    // Check if all permissions are granted
    public static boolean arePermissionsGranted(Context context) {
        return ContextCompat.checkSelfPermission(context, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, FINE_LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, COARSE_LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, INTERNET_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, ACCESS_NETWORK_STATE_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, RECEIVE_BOOT_COMPLETED_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, VIBRATE_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, WAKE_LOCK_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, FOREGROUND_SERVICE_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, READ_CONTACTS_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, WRITE_CONTACTS_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }

    // Request permissions
    public static void requestPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{
                        CAMERA_PERMISSION,
                        FINE_LOCATION_PERMISSION,
                        COARSE_LOCATION_PERMISSION,
                        READ_EXTERNAL_STORAGE_PERMISSION,
                        WRITE_EXTERNAL_STORAGE_PERMISSION,
                        POST_NOTIFICATIONS_PERMISSION,
                        INTERNET_PERMISSION,
                        ACCESS_NETWORK_STATE_PERMISSION,
                        RECEIVE_BOOT_COMPLETED_PERMISSION,
                        VIBRATE_PERMISSION,
                        WAKE_LOCK_PERMISSION,
                        FOREGROUND_SERVICE_PERMISSION,
                        READ_CONTACTS_PERMISSION,
                        WRITE_CONTACTS_PERMISSION
                }, PERMISSION_REQUEST_CODE);
    }

    // Handle the result of permission requests
    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            List<String> deniedPermissions = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permissions[i]);
                }
            }
            if (!deniedPermissions.isEmpty()) {
                // Handle the case where permissions were denied
                // For example, show a message to the user
            }
        }
    }
}
