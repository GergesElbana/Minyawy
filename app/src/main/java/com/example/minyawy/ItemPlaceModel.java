package com.example.minyawy;

public class ItemPlaceModel {

    String placeName;
    String PlaceDes;
    int itemPlacePhoto;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDes() {
        return PlaceDes;
    }

    public void setPlaceDes(String placeDes) {
        PlaceDes = placeDes;
    }

    public int getItemPlacePhoto() {
        return itemPlacePhoto;
    }

    public void setItemPlacePhoto(int itemPlacePhoto) {
        this.itemPlacePhoto = itemPlacePhoto;
    }

    public ItemPlaceModel(String placeName, String placeDes, int itemPlacePhoto) {
        this.placeName = placeName;
        PlaceDes = placeDes;
        this.itemPlacePhoto = itemPlacePhoto;
    }
}
