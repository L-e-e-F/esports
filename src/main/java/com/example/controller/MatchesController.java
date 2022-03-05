package com.example.controller;

import com.example.common.Result;
import com.example.enums.ResultCode;
import com.example.service.MatchesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/matches")
public class MatchesController {

    @Resource
    private MatchesService matchesService;

    @GetMapping("/all")
    public Result<?> selectALL(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo page = new PageInfo(matchesService.selectALL());
        if(page.getTotal() == 0)
            return Result.ErrorResult(ResultCode.ERROR_MATCHES);
        return Result.SuccessResult(page);
    }

    @GetMapping("/time")
    public Result<?> selectALLByTime(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Date time = new Date();

        PageInfo page = new PageInfo(matchesService.selectALLByTime(time));
        if(page.getTotal() == 0)
            return Result.ErrorResult(ResultCode.EMPTY_MATCHES);
        return Result.SuccessResult(page);
    }


}
