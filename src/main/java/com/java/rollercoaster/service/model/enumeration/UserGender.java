package com.java.rollercoaster.service.model.enumeration;

public enum UserGender {
    male("male"),
    female("female");
    private String sex;
    UserGender(String sex) {
        this.sex = sex;
    }
}
