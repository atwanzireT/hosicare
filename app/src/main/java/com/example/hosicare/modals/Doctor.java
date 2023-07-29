package com.example.hosicare.modals;

public class Doctor {
    String username, firstname, lastname, email, usertype, specilist, availability, hospitalid;

    public Doctor(){}

    public Doctor(String username, String firstname, String lastname, String email, String usertype, String specilist, String availability, String hospitalid) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.usertype = usertype;
        this.specilist = specilist;
        this.availability = availability;
        this.hospitalid = hospitalid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getSpecilist() {
        return specilist;
    }

    public void setSpecilist(String specilist) {
        this.specilist = specilist;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid;
    }
}
