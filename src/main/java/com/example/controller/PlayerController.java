package com.example.controller;

import com.example.common.Result;
import com.example.entity.Player;
import com.example.entity.User;
import com.example.enums.ResultCode;
import com.example.service.PlayerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Resource
    private PlayerService playerService;

    @GetMapping("/all")
    public Result<?> selectAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "1") int position,
                               @RequestParam(required = false, defaultValue = "") String name,
                               @RequestParam(required = false, defaultValue = "") String club) {
        PageHelper.startPage(pageNum, pageSize);
        System.out.println(name);
        PageInfo page =  new PageInfo(playerService.selectAll(position,name,club));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_PLAYER);
        return Result.SuccessResult(page);
    }

    @GetMapping("/userClubAll")
    public Result<?> userClubAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "1") int position,
                               @RequestParam(required = false, defaultValue = "") String name,
                               @RequestParam(required = false, defaultValue = "") String club) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, pageSize);
        System.out.println(name);
        PageInfo page =  new PageInfo(playerService.selectUserClubAll(user.getUserId(),position,name,club));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_PLAYER);
        return Result.SuccessResult(page);
    }

    @PostMapping("/set")
    public Result<?> Add(@RequestBody Player entity){
        System.out.println(entity);
        int row = playerService.insertSelective(entity);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PutMapping("/set")
    public Result<?> upload(@RequestBody Player entity){
        System.out.println(entity);
        int row = playerService.updateByPrimaryKey(entity);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_UPLOAD);
        }
    }

    @DeleteMapping("/set/{playerId}")
    public Result<?> delete(@PathVariable Long playerId){
        System.out.println(playerId);
        int row = playerService.deleteByPrimaryKey(playerId);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_DELETE);
        }
    }
}
