package com.example.service.impl;

import com.example.entity.Menu;
import com.example.mapper.MenuMapper;
import com.example.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    public List<Menu> listMenuByRoleId(Long roleId) {
        return menuMapper.listMenuByRoleId(roleId);
    }
}
