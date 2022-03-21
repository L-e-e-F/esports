package com.example.controller;

import com.example.entity.Role;
import com.example.enums.Roles;
import com.example.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return roleService.getAllUserType();
    }

    @GetMapping("/getRole")
    public String getRole(@RequestParam Long id){
        return Roles.valueOf(roleService.selectByPrimaryKey(id)).getName();
    }

    @GetMapping("/all")
    public List<Role> getAll(){
        return roleService.getAll();
    }
}
