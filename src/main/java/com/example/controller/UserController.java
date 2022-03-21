package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.enums.ResultCode;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/all")
    public Result<?> All(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                         @RequestParam(required = false, defaultValue = "1") Long group,
                         @RequestParam(required = false, defaultValue = "") String name){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page =  new PageInfo(userService.selectAll(group,name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_CLUB);
        return Result.SuccessResult(page);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
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
//            loginuser.setUrl("/frame");
            return Result.SuccessResult("/frame");
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        if(userService.register(user)>0)
            return Result.SuccessResult();
        return Result.ErrorResult();
    }

    @PostMapping("/set")
    public Result<?> Add(@RequestBody User entity){
        System.out.println(entity);
        int row = userService.register(entity);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PostMapping("/password")
    public Result<?> password(@RequestBody User entity){
        System.out.println(entity);
        int row = userService.password(entity);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_PASSWORD);
        }
    }

    @PostMapping("/follow")
    public Result<?> follow(@RequestParam Long club){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Long id = user.getUserId();
        int count = userService.selectFollow(id,club);
        if(count != 0){
            return Result.ErrorResult(ResultCode.TOO_FOLLOW);
        }else{
            int row = userService.follow(id,club);
            if(row != 0){
                return Result.SuccessResult();
            }else{
                return Result.ErrorResult(ResultCode.ERROR_FOLLOW);
            }
        }
    }

    @DeleteMapping("/follow")
    public Result<?> unfollow(@RequestParam Long club){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Long id = user.getUserId();
        int row = userService.unfollow(id,club);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_UNFOLLOW);
        }
    }

    @GetMapping("/ps")
    public Result<?> ps(@RequestParam String ps){
        boolean flag = userService.ps(ps);
        if(flag){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.PASSWORD_FOUND1);
        }
    }

    @PutMapping("/set")
    public Result<?> upload(@RequestBody User entity){
        System.out.println("put"+entity);
        int row = userService.updateByPrimaryKeySelective(entity);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_UPLOAD);
        }
    }

    @DeleteMapping("/set/{userId}")
    public Result<?> delete(@PathVariable Long userId){
        System.out.println(userId);
        int row = userService.deleteByPrimaryKey(userId);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_DELETE);
        }
    }

    @GetMapping("/LoginUser")
    public Result<?> LoginUser(){
//        Subject subject = SecurityUtils.getSubject();
        User Loginuser = (User) SecurityUtils.getSubject().getPrincipal();
//        System.out.println(subject);
        if(null != Loginuser){
            User u = new User();
            u.setUserId(Loginuser.getUserId());
            u.setName(Loginuser.getName());
            u.setNickName(Loginuser.getNickName());
            u.setRole(Loginuser.getRole());
//            System.out.println(u);
            return Result.SuccessResult(u);
        }
        return Result.ErrorResult(ResultCode.USER_NOT_LOGIN);
    }

    @GetMapping("/logout")
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}
