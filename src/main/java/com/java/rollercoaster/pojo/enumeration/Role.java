package com.java.rollercoaster.pojo.enumeration;

public enum Role {
    visitor("visitor"),
    manager("manager");
    private String status;
    Role(String status){
        this.status = status;
    }
}
