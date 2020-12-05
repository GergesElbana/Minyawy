package com.example.minyawy;

public class FetchPlaceName {
  private String photo;
  private String name;
  private String descrip;

    public FetchPlaceName() {
    }

    public FetchPlaceName(String photo, String name, String descrip) {
        this.photo = photo;
        this.name = name;
        this.descrip = descrip;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
