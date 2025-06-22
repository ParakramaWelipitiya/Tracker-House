package com.welipitiya.trackerhouse;

public class Bike {
    private String name;           // Bike name
    private String description;    // Description of the bike
    private int imageResource;     // Image resource ID for the bike picture
    private boolean isFavorite;    // Is this bike marked as favorite?

    // Constructor to create a bike object with all details
    public Bike(String name, String description, int imageResource, boolean isFavorite) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.isFavorite = isFavorite;
    }

    // Empty constructor - sometimes needed for databases or adapters
    public Bike() {
    }

    // Getters to get bike details

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    // Setters to update bike details

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
