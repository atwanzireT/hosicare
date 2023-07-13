package com.example.hosicare.modals;

public class HospitalModal {
    String name, postoffice, location, level;

//    public HospitalModal(){};
    public HospitalModal(String name, String postoffice, String location, String level) {
        this.name = name;
        this.postoffice = postoffice;
        this.location = location;
        this.level = level;
    }

    public HospitalModal(){}

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
