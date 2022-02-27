package com.example.controller;

import com.example.entity.Matches;
import com.example.service.MatchesService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/matches")
public class MatchesController {

    @Resource
    private MatchesService matchesService;

    @RequestMapping("/all")
    public Page<Matches> selectALL(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return matchesService.selectALL();
    }

}
