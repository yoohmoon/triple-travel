package com.triple.travelLog.entity;

import jakarta.persistence.*;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int dayNumber;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private PlaceCategory category;

    private String address;
    private double rating;
    private double lat;
    private double lng;

    protected Place(){}

    public Place(String name, int dayNumber, Trip trip, PlaceCategory category, String address, double rating, double lat, double lng) {
        this.name = name;
        this.dayNumber = dayNumber;
        this.trip = trip;
        this.category = category;
        this.address = address;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public Trip getTrip() {
        return trip;
    }

    public PlaceCategory getCategory() {
        return category;
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
