package com.example.service;

import com.example.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> listMenuByRoleId(Long roleId);

}
