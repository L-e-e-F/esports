package com.example.service;

import com.example.entity.Role;

import java.util.List;

public interface RoleService {

    String selectByPrimaryKey(Long id);

    List<Role> getAllUserType();

    List<Role> getAll();
}
