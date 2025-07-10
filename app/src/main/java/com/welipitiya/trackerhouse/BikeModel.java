package com.welipitiya.trackerhouse;

public class BikeModel {
    private String name;
    private int imageResId;
    private String description;
    private String price;
    private boolean isFavorite;

    public BikeModel(String name, int imageResId, String description, String price, boolean isFavorite) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
        this.price = price;
        this.isFavorite = isFavorite;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

}
