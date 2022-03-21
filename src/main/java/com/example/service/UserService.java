package com.example.service;

import com.example.entity.User;
import com.github.pagehelper.Page;


public interface UserService {

    User getUserByName(String name);

    String getNickNameByName(String name);

    int register(User user);

    Page<User> selectAll(Long group, String name);

    int deleteByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int password(User record);

    boolean ps(String ps);

    int follow(Long user,Long club);

    int selectFollow(Long user,Long club);

    int unfollow(Long user, Long club);
}
