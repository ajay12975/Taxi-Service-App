package com.example.mytaxiservice.tripAddress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaxiservice.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecentAddressAdapter extends RecyclerView.Adapter<RecentAddressAdapter.ViewHolder> {

    private List<RecentAddressResponse.Datum> list;
    private OnAddressClickListener listener;

    // Constructor to pass list and the click listener
    public RecentAddressAdapter(List<RecentAddressResponse.Datum> list, OnAddressClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecentAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_trip, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentAddressAdapter.ViewHolder holder, int position) {
        // Original date string from list
        String originalDate = list.get(holder.getAdapterPosition()).getCreatedAt();

        // Define the current date format of the input string
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        // Define the desired date format
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");

        try {
            // Parse the original date string into a Date object
            Date date = inputFormat.parse(originalDate);
            // Format the Date object into the desired format
            String formattedDate = outputFormat.format(date);
            // Set the formatted date to the TextView
            holder.dateofTrip.setText(formattedDate);

        } catch (ParseException e) {
            e.printStackTrace();
            holder.dateofTrip.setText(originalDate); // Fallback to original date if parsing fails
        }

        // Set the suggested address
        holder.suggestedTripf.setText(list.get(holder.getAdapterPosition()).getAddress());

        // Handle item click using the dynamic position
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensure the position is dynamically retrieved when clicked
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    listener.onAddressClick(list.get(currentPosition).getAddress());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dateofTrip, suggestedTripf;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateofTrip = itemView.findViewById(R.id.tvDateTrip);
            suggestedTripf = itemView.findViewById(R.id.tvSuggestedPlace);
            linearLayout = itemView.findViewById(R.id.lnrLayout);
        }
    }

    // Interface to handle address click events
    public interface OnAddressClickListener {
        void onAddressClick(String address);
    }
}
