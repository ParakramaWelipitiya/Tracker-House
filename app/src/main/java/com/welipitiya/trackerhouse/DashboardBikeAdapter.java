package com.welipitiya.trackerhouse;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DashboardBikeAdapter extends RecyclerView.Adapter<DashboardBikeAdapter.BikeViewHolder> {

    private List<BikeModel> bikeList;
    private Context context;
    private DBHelper dbHelper;

    public DashboardBikeAdapter(List<BikeModel> bikeList, Context context) {
        this.bikeList = bikeList;
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bike_dashboard, parent, false);
        return new BikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeViewHolder holder, int position) {
        BikeModel bike = bikeList.get(position);
        holder.tvName.setText(bike.getName());
        holder.tvPrice.setText(bike.getPrice());
        holder.image.setImageResource(bike.getImageResId());

        if (dbHelper.isFavorite(bike.getName())) {
            holder.btnFav.setImageResource(R.drawable.ic_fav_filled);
            bike.setFavorite(true);
        } else {
            holder.btnFav.setImageResource(R.drawable.ic_fav_border);
            bike.setFavorite(false);
        }

        holder.btnFav.setOnClickListener(v -> {
            if (bike.isFavorite()) {
                dbHelper.removeFavorite(bike.getName());
                bike.setFavorite(false);
                holder.btnFav.setImageResource(R.drawable.ic_fav_border);
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addFavorite(bike.getName(), bike.getImageResId(), bike.getDescription(), bike.getPrice());
                bike.setFavorite(true);
                holder.btnFav.setImageResource(R.drawable.ic_fav_filled);
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });

        // WhatsApp button click
        holder.btnWhatsapp.setOnClickListener(v -> {
            String message = "Hello,\n\n" +
                    "I came across your listing for the following bike on the Tracker House app:\n\n" +
                    "🚲 *" + bike.getName() + "*\n" +
                    "💰 Price: *" + bike.getPrice() + "*\n\n" +
                    "I'm interested in learning more details regarding its current availability, condition, and documentation.\n\n" +
                    "Please let me know if it's still available and how I may proceed with the inquiry or inspection.\n\n" +
                    "Thank you.\nBest regards.";

            String phoneNumber = "94754357288";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.whatsapp");
            intent.setData(Uri.parse("https://wa.me/" + phoneNumber + "?text=" + Uri.encode(message)));

            try {
                context.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bikeList.size();
    }

    public static class BikeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvName, tvPrice;
        ImageButton btnFav, btnWhatsapp;

        public BikeViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivBikeImage);
            tvName = itemView.findViewById(R.id.tvBikeName);
            tvPrice = itemView.findViewById(R.id.tvBikePrice);
            btnFav = itemView.findViewById(R.id.btnFav);
            btnWhatsapp = itemView.findViewById(R.id.btnWhatsapp);
        }
    }
}

