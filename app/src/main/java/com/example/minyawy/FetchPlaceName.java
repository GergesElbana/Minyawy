package com.example.minyawy;

public class FetchPlaceName {
  private    String logo;
  private   String PlaceName;
  private   String PlaceDescrip;

    public FetchPlaceName() {
    }

    public FetchPlaceName(String logo, String placeName, String placeDescrip) {
        this.logo = logo;
        PlaceName = placeName;
        PlaceDescrip = placeDescrip;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public String getPlaceDescrip() {
        return PlaceDescrip;
    }

    public void setPlaceDescrip(String placeDescrip) {
        PlaceDescrip = placeDescrip;
    }
}
