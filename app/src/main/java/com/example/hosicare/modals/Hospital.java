package com.example.hosicare.modals;

public class Hospital {
    String name, postoffice, location, level;

    public Hospital(String name, String postoffice, String location, String level) {
        this.name = name;
        this.postoffice = postoffice;
        this.location = location;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostoffice() {
        return postoffice;
    }

    public void setPostoffice(String postoffice) {
        this.postoffice = postoffice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
