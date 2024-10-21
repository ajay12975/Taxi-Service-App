package com.example.mytaxiservice.utils;


import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UtilCalender{

    // Method to show DatePicker and set selected date on a TextView
    public static void showDatePickerWithTextView(Context context, final TextView textView, String dateFormatPattern) {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Format the selected date and set it to the TextView
                        String formattedDate = formatDate(selectedYear, selectedMonth, selectedDay, dateFormatPattern);
                        textView.setText(formattedDate);
                    }
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }

    // Method to format a date in a specific pattern
    public static String formatDate(int year, int month, int day, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }
}
