package com.example.demo2;

public class Locationhelper
{
    private double lan;
    private double lat;

    public Locationhelper(double lan, double lat) {
        this.lan = lan;
        this.lat = lat;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
