package com.example.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {
    /**
     * 菜单id
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单url
     */
    private String url;

    private static final long serialVersionUID = 1L;
}