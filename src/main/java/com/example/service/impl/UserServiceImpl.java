package com.example.service.impl;

import com.example.config.MD5.MD5;
import com.example.entity.User;
import com.example.enums.ResultCode;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

//    public User login(User user) {
//        User one =  userMapper.login(user.getName(),user.getPassword());
//        return one;
//    }

    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public String getNickNameByName(String name) {
        return userMapper.getNickNameByName(name);
    }

    @Override
    public int register(User user) {
        if(userMapper.getUserByName(user.getName())!=null){
            throw new CustomException(ResultCode.USER_IS_FOUND);
        }
        try {
            String md5password = MD5.SysMd5(user.getName(), user.getPassword());
            user.setPassword(md5password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMapper.insertSelective(user);
    }
}
