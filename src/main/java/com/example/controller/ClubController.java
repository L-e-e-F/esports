package com.example.controller;

import com.example.common.Result;
import com.example.entity.Club;
import com.example.enums.ResultCode;
import com.example.service.ClubService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        PageInfo page =  new PageInfo(clubService.selectALL(group,name));
        if (page.getTotal() == 0) return Result.ErrorResult(ResultCode.ERROR_CLUB);
        return Result.SuccessResult(page);
    }

    @GetMapping("/ALL")
    public Result<?> ALL(@RequestParam(required = false, defaultValue = "") String group){
        return Result.SuccessResult(clubService.ALL(group));
    }

    @GetMapping("/eventclub/{id}")
    public Result<?> eventclub(@PathVariable Long id){
        return Result.SuccessResult(clubService.selectEventClub(id));
    }

    @PostMapping("/set")
    public Result<?> Add(@RequestBody Club entity){
        System.out.println(entity);
        int row = clubService.insertSelective(entity);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_INSERT);
        }
    }

    @PutMapping("/set")
    public Result<?> upload(@RequestBody Club entity){
        System.out.println(entity.getClubImg());
        int row = clubService.updateByPrimaryKeySelective(entity);
        if(row != 0){
            return Result.SuccessResult();
        }else{
            return Result.ErrorResult(ResultCode.ERROR_UPLOAD);
        }
    }

    @DeleteMapping("/set/{clubId}")
    public Result<?> delete(@PathVariable Long clubId){
            System.out.println(clubId);
            int row = clubService.deleteByPrimaryKey(clubId);
            if(row != 0){
                return Result.SuccessResult();
            }else{
                return Result.ErrorResult(ResultCode.ERROR_DELETE);
            }
    }

    @PostMapping("/set/img")
    public Result<?> upload(MultipartFile file){
        System.out.println(file);
        if (file.isEmpty()){
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
