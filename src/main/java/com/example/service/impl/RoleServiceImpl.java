package com.example.service.impl;

import com.example.entity.Role;
import com.example.mapper.RoleMapper;
import com.example.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public String selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id).getRole();
    }

    @Override
    public List<Role> getAllUserType() {
        return roleMapper.getAllUserType();
    }

    @Override
    public List<Role> getAll() {
        return roleMapper.getAll();
    }
}
