package com.example.controller;

import com.example.entity.Role;
import com.example.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/roleType")
    public List<Role> getAllUserType(){
        System.out.println("2"+roleService.getAllUserType());
        return roleService.getAllUserType();
    }
}
