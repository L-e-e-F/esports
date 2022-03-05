package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.enums.ResultCode;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

//    @Resource
//    private UserTest userTest;
//
//    @GetMapping("/all")
//    public User findAll() {
//        System.out.println(1);
//        return userTest.test();
//    }


    @PostMapping("/login")
    public Result<?> login(@RequestBody User user, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try{
            subject.login(token);
        }catch (UnknownAccountException e) {
            return Result.ErrorResult(ResultCode.USER_NOT_FOUND);
        } catch (IncorrectCredentialsException e) {
            return Result.ErrorResult(ResultCode.PASSWORD_FOUND);
        }  catch (Exception e) {
            e.printStackTrace();
            return Result.ErrorResult(e.getMessage());
        }
//        LoginUser loginuser = new LoginUser();
//        User u = (User) SecurityUtils.getSubject().getPrincipal();
//        loginuser.setNickName(u.getNickName());
//        loginuser.setRole(u.getRole());
        SavedRequest savedRequest=WebUtils.getSavedRequest(request);
        if(null != savedRequest){
//            loginuser.setUrl(WebUtils.getSavedRequest(request).getRequestUrl());
            return Result.SuccessResult(WebUtils.getSavedRequest(request).getRequestUrl());
        }
        else{
//            loginuser.setUrl("/frame");
            return Result.SuccessResult("/frame");
        }
//        return Result.SuccessResult(loginuser);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        if(userService.register(user)>0)
            return Result.SuccessResult();
        return Result.ErrorResult();
    }

    @GetMapping("/LoginUser")
    public Result<?> LoginUser(){
        User Loginuser = (User) SecurityUtils.getSubject().getPrincipal();
        if(null != Loginuser){
            User u = new User();
            u.setNickName(Loginuser.getNickName());
            u.setRole(Loginuser.getRole());
            System.out.println(u);
            return Result.SuccessResult(u);
        }
        return Result.ErrorResult(ResultCode.USER_NOT_LOGIN);
    }

}
