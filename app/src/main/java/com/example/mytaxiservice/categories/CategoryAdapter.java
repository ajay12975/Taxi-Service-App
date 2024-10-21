package com.example.mytaxiservice.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytaxiservice.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoriesResponse.Datum> categories;

    public CategoryAdapter(List<CategoriesResponse.Datum> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_customer, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvName.setText(categories.get(position).getName());
        // Use Glide to load the image
        Glide.with(holder.itemView.getContext())
                .load(categories.get(position).getImage()) // Assuming getImageUrl() returns the image URL
                .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
                .error(R.drawable.ic_launcher_foreground) // Optional error image
                .into(holder.imgImage);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPrices,tvName;
        private ImageView imgImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrices = itemView.findViewById(R.id.tvPrices);
            imgImage = itemView.findViewById(R.id.imgImage);
        }
    }
}
