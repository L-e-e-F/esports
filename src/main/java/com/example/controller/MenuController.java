package com.example.controller;

import com.example.common.Result;
import com.example.entity.Menu;
import com.example.enums.ResultCode;
import com.example.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/url")
    public Result<?> getUrl(@RequestParam Long role){
        System.out.println(role);
        try{
            List<Menu> list = menuService.listMenuByRoleId(role);
            System.out.println(list);
            if(list.isEmpty()){
                return Result.ErrorResult(ResultCode.MENU_EMPTY);
            }
            return Result.SuccessResult(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.ErrorResult(ResultCode.USER_NOT_LOGIN);
    }
}
