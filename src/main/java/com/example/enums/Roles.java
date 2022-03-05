package com.example.enums;

public enum Roles {
    ROLE_ADMIN( "admin"),
    ROLE_USER("user"),
    ROLE_CLUB("club"),
    ROLE_CHAMPIONSHIP("championship");
    final private String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
