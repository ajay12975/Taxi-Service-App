package com.example.mytaxiservice.utils;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class UtilsCameraBase64 {

    // Convert Bitmap to Base64 String
    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    // Convert Base64 String to Bitmap
    public static Bitmap base64ToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}


//useees


//package com.example.taxiservice;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.widget.ImageView;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.taxiservice.utils.UtilsCameraBase64;
//
//public class MainActivity extends AppCompatActivity {
//
//    private ImageView imageView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        imageView = findViewById(R.id.imageView);
//
//        // Example Bitmap (you can load your own image here)
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_image);
//
//        // Convert Bitmap to Base64
//        String base64String = UtilsCameraBase64.bitmapToBase64(bitmap);
//        // Optionally, you can log the Base64 string or send it to a server
//
//        // Convert Base64 back to Bitmap
//        Bitmap decodedBitmap = UtilsCameraBase64.base64ToBitmap(base64String);
//
//        // Display the decoded Bitmap in the ImageView
//        imageView.setImageBitmap(decodedBitmap);
//    }
//}


