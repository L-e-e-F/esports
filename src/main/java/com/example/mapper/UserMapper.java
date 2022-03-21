package com.example.mapper;

import com.example.entity.User;
import com.github.pagehelper.Page;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Page<User> selectAll(Long group,String name);

    User login(String name,String password);

    User getUserByName(String name);

    String getNickNameByName(String name);

    int follow(Long user,Long club);

    int selectFollow(Long user,Long club);

    int unfollow(Long user, Long club);
}