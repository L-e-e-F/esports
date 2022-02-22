package com.example.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 角色id
     */
    private Long role;

    /**
     * 昵称
     */
    private String nickName;

    private static final long serialVersionUID = 1L;
}