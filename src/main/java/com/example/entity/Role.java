package com.example.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名
     */
    private String description;

    /**
     * 角色类型
     */
    private String role;

    private static final long serialVersionUID = 1L;
}