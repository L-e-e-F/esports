package com.example.controller;

import com.example.common.Result;
import com.example.entity.Club;
import com.example.entity.User;
import com.example.enums.ResultCode;
import com.example.service.ClubService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Resource
    private ClubService clubService;

    @GetMapping("/all")
    public Result<?> selectAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "S") String group,
                               @RequestParam(required = false, defaultValue = "") String name) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(clubService.selectALL(group, name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_CLUB);
        return Result.SuccessResult(page);
    }

    @GetMapping("/follow")
    public Result<?> follow(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                            @RequestParam(required = false, defaultValue = "") String club) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(clubService.follow(user.getUserId(), club));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_CLUB);
        return Result.SuccessResult(page);
    }

    @GetMapping("/NotUser")
    public Result<?> NotUser(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                             @RequestParam(required = false, defaultValue = "S") String group,
                             @RequestParam(required = false, defaultValue = "") String name) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(clubService.selectNotUserClub(group, name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_CLUB);
        return Result.SuccessResult(page);
    }

    @GetMapping("/user")
    public Result<?> selectUserClub(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                                    @RequestParam(required = false, defaultValue = "S") String group,
                                    @RequestParam(required = false, defaultValue = "") String name) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(clubService.selectUserClub(group, name, user.getUserId()));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_CLUB);
        return Result.SuccessResult(page);
    }

    @GetMapping("/userList")
    public Result<?> selectUserClubList(@RequestParam(required = false, defaultValue = "") String group) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return Result.SuccessResult(clubService.selectUserClubList(group, user.getUserId()));
    }

    @GetMapping("/ALL")
    public Result<?> ALL(@RequestParam(required = false, defaultValue = "") String group) {
        return Result.SuccessResult(clubService.ALL(group));
    }

    @GetMapping("/eventClub/{id}")
    public Result<?> eventClub(@PathVariable Long id) {
        return Result.SuccessResult(clubService.selectEventClub(id));
    }

    @PostMapping("/set")
    public Result<?> Add(@RequestBody Club entity) {
        System.out.println(entity);
        int row = clubService.insertSelective(entity);
        if (row != 0) {
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PostMapping("/userSet")
    public Result<?> AddUserClub(@RequestBody Club entity) {
        System.out.println(entity);
        int row = clubService.insertSelective(entity);
        if (row != 0) {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            Long id = entity.getClubId();
            clubService.insertUserClub(user.getUserId(), id);
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PostMapping("/acquisition")
    public Result<?> acquisition(@RequestParam Long club) {
        System.out.println(club);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        try {
            clubService.insertUserClub(user.getUserId(), club);
        } catch (Exception e) {
            return Result.ErrorResult(ResultCode.NOT_ACQUISITION);
        }
        return Result.SuccessResult();
    }

    @PutMapping("/set")
    public Result<?> upload(@RequestBody Club entity) {
        System.out.println(entity.getClubImg());
        int row = clubService.updateByPrimaryKeySelective(entity);
        if (row != 0) {
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_UPLOAD);
        }
    }

    @DeleteMapping("/set/{clubId}")
    public Result<?> delete(@PathVariable Long clubId) {
        System.out.println(clubId);
        int row = clubService.deleteByPrimaryKey(clubId);
        if (row != 0) {
            return Result.SuccessResult();
        } else {
            return Result.ErrorResult(ResultCode.ERROR_DELETE);
        }
    }

    @DeleteMapping("/userSet/{clubId}")
    public Result<?> deleteUserClub(@PathVariable Long clubId) {
        System.out.println(clubId);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        try {
            clubService.deleteUserClub(user.getUserId(), clubId);

        } catch (Exception e) {
            return Result.ErrorResult(ResultCode.NOT_SELL);
        }
        return Result.SuccessResult();
    }

    @PostMapping("/set/img")
    public Result<?> upload(MultipartFile file) {
        System.out.println(file);
        if (file.isEmpty()) {
            return Result.ErrorResult(ResultCode.EMPTY_FILE);
        }
//        synchronized (ClubController.class) {
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/images/";
        String name = "../images/";
        String clubImg;
        File temp = new File(filePath);
        if (!temp.exists()) {
            temp.mkdirs();
        }
        File localFile = new File(filePath + filename);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            Thread.sleep(1L);
        } catch (Exception e) {
            e.printStackTrace();
            Result.ErrorResult(e.getMessage());
        }
        clubImg = name + filename;
        return Result.SuccessResult(clubImg);
//        }
    }

}
