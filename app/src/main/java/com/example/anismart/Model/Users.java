package com.example.anismart.Model;

public class Users {

    public String email, username;

    public Users() {

    }

    public Users(String Uid, String email, String Username) {
        this.email = email;
        this.username = Username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
