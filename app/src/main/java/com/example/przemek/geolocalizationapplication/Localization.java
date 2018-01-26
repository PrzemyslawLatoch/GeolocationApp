package com.example.przemek.geolocalizationapplication;

import java.util.UUID;

/**
 * Created by Przemek on 26.01.2018.
 */

class Localization {

    private final double latitude;
    private final double longitude;
    private UUID id;
    private final String name;
    private final String desc;
    private final double radius;

    public Localization(UUID Id, String name, String desc, double radius, double lat, double lang) {
        this.id = Id;
        this.name = name;
        this.desc = desc;
        this.radius = radius;
        this.longitude = lat;
        this.latitude = lang;
    }

    public UUID getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public double getRadius() {
        return radius;
    }
}
