package com.example.minyawy;

public class FechData {
    String logo;
    String placeName;
    String location ;
    String number;

    public FechData(String logo, String placeName, String location) {
        this.logo = logo;
        this.placeName = placeName;
        this.location = location;

    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
