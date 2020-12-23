package com.example.minyawy;

public class UserModel {
    private String email;
    private String Password;
    private String Name;
    private String Photo;

    public UserModel(String username, String password, String name) {
        email = username;
        Password = password;
        Name = name;
    }

    public UserModel(String email, String password, String name, String photo) {
        email = email;
        Password = password;
        Name = name;
        Photo = photo;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        email = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
