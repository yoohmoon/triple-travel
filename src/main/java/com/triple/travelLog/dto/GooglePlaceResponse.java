package com.triple.travelLog.dto;

public class GooglePlaceResponse {
    private String name;
    private String address;
    private double rating;
    private double lat;
    private double lng;

    public GooglePlaceResponse(String name, String address, double rating, double lat, double lng) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
