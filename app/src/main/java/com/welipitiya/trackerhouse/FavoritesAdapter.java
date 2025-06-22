package com.welipitiya.trackerhouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<Bike> favoriteList; // List of favorite bikes to show
    private Context context;          // Context for layout inflater

    // Constructor to initialize bike list and context
    public FavoritesAdapter(List<Bike> favoriteList, Context context) {
        this.favoriteList = favoriteList;
        this.context = context;
    }

    // ViewHolder class holds references to views for each list item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bikeImage;      // Image of bike
        TextView bikeName;        // Name of bike
        TextView bikeDescription; // Description of bike

        public ViewHolder(View itemView) {
            super(itemView);
            bikeImage = itemView.findViewById(R.id.bikeImage);
            bikeName = itemView.findViewById(R.id.bikeName);
            bikeDescription = itemView.findViewById(R.id.bikeDescription);
        }
    }

    // Called to create a new ViewHolder when needed
    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each bike item in favorites list
        View view = LayoutInflater.from(context).inflate(R.layout.bike_item_favorite, parent, false);
        return new ViewHolder(view);
    }

    // Called to bind data to views in ViewHolder for each item position
    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        Bike bike = favoriteList.get(position);
        holder.bikeName.setText(bike.getName());                 // Set bike name text
        holder.bikeDescription.setText(bike.getDescription());   // Set bike description text
        holder.bikeImage.setImageResource(bike.getImageResource()); // Set bike image resource
    }

    // Return total number of favorite bikes
    @Override
    public int getItemCount() {
        return favoriteList.size();
    }
}
