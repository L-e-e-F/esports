package com.example.enums;

public enum Roles {
    ROLE_ADMIN(1, "管理员"),
    ROLE_USER(2, "普通用户"),
    ROLE_CLUB(3, "俱乐部"),
    ROLE_CHAMPIONSHIP(4, "赛事方");
    final private int code;
    final private String name;

    Roles(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
