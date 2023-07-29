package com.example.hosicare.modals;

public class BloodModal {
    String type, hosId;

    public BloodModal(){}
    public BloodModal(String type, String hosId) {
        this.type = type;
        this.hosId = hosId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }
}
