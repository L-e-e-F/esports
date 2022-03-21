package com.example.service.impl;

import com.example.config.MD5.MD5;
import com.example.entity.User;
import com.example.enums.ResultCode;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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

    @Override
    public Page<User> selectAll(Long group, String name) {
        return userMapper.selectAll(group,name);
    }

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        System.out.println("up"+record);
        if(!Objects.equals(record.getPassword(), "")){
            try {
                String md5password = MD5.SysMd5(record.getName(), record.getPassword());
                record.setPassword(md5password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            record.setPassword(null);
        }
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int password(User record) {
        try {
            String md5password = MD5.SysMd5(record.getName(), "123456");
            record.setPassword(md5password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public boolean ps(String ps) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String password = MD5.SysMd5(user.getName(), ps);
        return Objects.equals(password, user.getPassword());
    }

    @Override
    public int follow(Long user, Long club) {
        return userMapper.follow(user,club);
    }

    @Override
    public int selectFollow(Long user, Long club) {
        return userMapper.selectFollow(user,club);
    }

    @Override
    public int unfollow(Long user, Long club) {
        return userMapper.unfollow(user,club);
    }
}
