package com.example.hosicare.modals;

public class MedicineModal {
    private String name, age, treatment;

    public MedicineModal(String name, String age, String treatment) {
        this.name = name;
        this.age = age;
        this.treatment = treatment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
