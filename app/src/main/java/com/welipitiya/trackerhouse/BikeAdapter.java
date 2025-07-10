package com.welipitiya.trackerhouse;

import android.app.AlertDialog;
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

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.BikeViewHolder> {

    private Context context;
    private List<BikeModel> bikeList;
    private DBHelper dbHelper;

    public BikeAdapter(Context context, List<BikeModel> bikeList) {
        this.context = context;
        this.bikeList = bikeList;
        this.dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bike, parent, false);
        return new BikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeViewHolder holder, int position) {
        BikeModel bike = bikeList.get(position);
        holder.bikeName.setText(bike.getName());
        holder.bikePrice.setText(bike.getPrice());
        holder.bikeImage.setImageResource(bike.getImageResId());

        // Set favorite icon
        if (dbHelper.isFavorite(bike.getName())) {
            holder.favoriteButton.setImageResource(R.drawable.ic_fav_filled); // update with filled heart icon
            bike.setFavorite(true);
        } else {
            holder.favoriteButton.setImageResource(R.drawable.ic_fav_border);
            bike.setFavorite(false);
        }

        // Favorite icon click
        holder.favoriteButton.setOnClickListener(v -> {
            if (bike.isFavorite()) {
                dbHelper.removeFavorite(bike.getName());
                bike.setFavorite(false);
                holder.favoriteButton.setImageResource(R.drawable.ic_fav_border);
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addFavorite(bike.getName(), bike.getImageResId(), bike.getDescription(), bike.getPrice());
                bike.setFavorite(true);
                holder.favoriteButton.setImageResource(R.drawable.ic_fav_filled);
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });

        // Item click: show popup
        holder.itemView.setOnClickListener(v -> showPopup(bike));
    }

    @Override
    public int getItemCount() {
        return bikeList.size();
    }

    public static class BikeViewHolder extends RecyclerView.ViewHolder {
        TextView bikeName, bikePrice;
        ImageView bikeImage;
        ImageButton favoriteButton;

        public BikeViewHolder(@NonNull View itemView) {
            super(itemView);
            bikeName = itemView.findViewById(R.id.bike_name);
            bikePrice = itemView.findViewById(R.id.bike_price);
            bikeImage = itemView.findViewById(R.id.bike_image);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
        }
    }

    private void showPopup(BikeModel bike) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View popupView = LayoutInflater.from(context).inflate(R.layout.bike_details_popup, null);

        ImageView popupImage = popupView.findViewById(R.id.popup_bike_image);
        TextView popupName = popupView.findViewById(R.id.popup_bike_name);
        TextView popupPrice = popupView.findViewById(R.id.popup_bike_price);
        TextView popupDesc = popupView.findViewById(R.id.popup_bike_description);
        ImageButton closeBtn = popupView.findViewById(R.id.btn_popup_close);
        ImageButton popupFavBtn = popupView.findViewById(R.id.popup_favorite_button);

        popupImage.setImageResource(bike.getImageResId());
        popupName.setText(bike.getName());
        popupPrice.setText(bike.getPrice());
        popupDesc.setText(bike.getDescription());

        if (bike.isFavorite()) {
            popupFavBtn.setImageResource(R.drawable.ic_fav_filled);
        } else {
            popupFavBtn.setImageResource(R.drawable.ic_fav_border);
        }

        popupFavBtn.setOnClickListener(v -> {
            if (bike.isFavorite()) {
                dbHelper.removeFavorite(bike.getName());
                bike.setFavorite(false);
                popupFavBtn.setImageResource(R.drawable.ic_fav_border);
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            } else {
                dbHelper.addFavorite(bike.getName(), bike.getImageResId(), bike.getDescription(), bike.getPrice());
                bike.setFavorite(true);
                popupFavBtn.setImageResource(R.drawable.ic_fav_filled);
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        //whatsapp button
        ImageButton whatsappBtn = popupView.findViewById(R.id.popup_whatsapp_button);

        whatsappBtn.setOnClickListener(v -> {
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


        builder.setView(popupView);
        AlertDialog dialog = builder.create();

        closeBtn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
