package com.example.service;

import com.example.entity.Role;

import java.util.List;

public interface RoleService {

    String getRoleById(Long id);

    List<Role> getAllUserType();;
}