package com.example.minyawy.Admin;

public class Admin_SendData {
    String photo;
    String name;
    String descrip;
    String location;
    String number;
    private String id;
    String photo_1;
    String photo_2;

    public Admin_SendData() {
    }

    public Admin_SendData(String photo_1) {
        this.photo_1 = photo_1;
       // this.photo_2 = photo_2;
    }

    public Admin_SendData(String photo, String name, String descrip, String location, String number, String id) {
        this.photo = photo;
        this.name = name;
        this.descrip = descrip;
        this.location = location;
        this.number = number;
        this.id=id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto_1() {
        return photo_1;
    }

    public void setPhoto_1(String photo_1) {
        this.photo_1 = photo_1;
    }

    public String getPhoto_2() {
        return photo_2;
    }

    public void setPhoto_2(String photo_2) {
        this.photo_2 = photo_2;
    }
}
