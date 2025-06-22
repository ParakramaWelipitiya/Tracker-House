package com.welipitiya.trackerhouse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.welipitiya.trackerhouse.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    // List of image resource IDs to be displayed
    private List<Integer> images;

    // Constructor to receive the image list
    public ImageAdapter(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    // Inflates the layout for each image item
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    // Binds the image at the current position to the ImageView
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(images.get(position));
    }

    @Override
    // Returns the total number of images
    public int getItemCount() {
        return images.size();
    }

    // ViewHolder class holds reference to the ImageView for each item
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            // Link the ImageView from the layout
            imageView = itemView.findViewById(R.id.image_slider);
        }
    }
}
