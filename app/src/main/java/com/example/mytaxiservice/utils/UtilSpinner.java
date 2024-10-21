package com.example.mytaxiservice.utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class UtilSpinner {

    // Method to populate a spinner with a list of items
    public static void setUpSpinner(Context context, Spinner spinner, List<String> itemList) {
        // Creating an ArrayAdapter with the list and setting it to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, itemList);
        // Specify the layout for dropdown items
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Set the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
