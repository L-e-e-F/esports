package com.example.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 登录后跳转链接
     */
    private String url;

    private static final long serialVersionUID = 1L;
}
