package com.example.hosicare.modals;

public class HospitalModal {
    String name, postoffice, location, level, hosiID;

    public HospitalModal(String name, String postoffice, String location, String level, String hosiID) {
        this.name = name;
        this.postoffice = postoffice;
        this.location = location;
        this.level = level;
        this.hosiID = hosiID;
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

    public String getHosiID() {
        return hosiID;
    }

    public void setHosiID(String hosiID) {
        this.hosiID = hosiID;
    }
}
