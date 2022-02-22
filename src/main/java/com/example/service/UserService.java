package com.example.service;

import com.example.entity.User;


public interface UserService {

    User getUserByName(String name);

    String getNickNameByName(String name);

    int register(User user);

}
