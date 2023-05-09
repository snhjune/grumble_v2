package com.example.foodapp;

//class to map restaurant data to store and display

public class Restaurant {
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double distance;
    private String photoReference;
    private int priceLevel;
    private float rating;
    private String placeId;

    //getter functions for restaurant class attributes
    public String getName() {
        return name;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public int getPriceLevel() { return priceLevel; }
    public float getRating() { return rating; }
    public String getPlaceId() { return placeId; }
    public String getPhotoReference() { return photoReference; }
    public String getAddress() { return this.address; }

    //setter functions
    public void setName(String name) { this.name = name;}
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setPhotoReference(String photoReference) { this.photoReference = photoReference; }
    public void setRating(float rating) { this.rating = rating; }
    public void setPriceLevel(int priceLevel) { this.priceLevel = priceLevel; }
    public void setPlaceId(String placeId) { this.placeId = placeId; }
    public void setAddress(String vicinity) { this.address = vicinity; }

    @Override
    public String toString() {
        return new StringBuilder().append(getName())
                .append("; Rating: ")
                .append(getRating())
                .append("; Price Level: ")
                .append(getPriceLevel()).toString();
    }
}
