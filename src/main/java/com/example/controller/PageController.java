package com.example.controller;

import com.example.common.Result;
import com.example.enums.ResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/")
public class PageController {

    @GetMapping
    public String hello() {
        return "login";
    }

    @GetMapping("{page}")
    public String toPage(@PathVariable String page) {
        System.out.println(page);
        return page;
    }

    @RequestMapping("Unauthorized")
    @ResponseBody
    public Result<?> unauthorized(){
        return Result.ErrorResult(ResultCode.UN_PERMISSION);
    }

//    @GetMapping("favicon.ico")
//    @ResponseBody
//    void noFavicon() {
//    }
//    @RequestMapping("Index")
//    public String toIndex(){ return "index";}
//    @RequestMapping("Test")
//    public String toTest(){ return "test";}
//    @RequestMapping("css/{rs}")
//    public String toCss(@PathVariable String rs) {
//        return "css/"+rs;
//    }
    //    @RequestMapping("/Login")
//    public String toLogin() {
//        return "front/login";
//    }
//
//    @RequestMapping("/Index")
//    public String toIndex() {
//        return "index";
//    }
//
////    @RequiresRoles(value={"admin"})
//    @GetMapping("/add")
//    public String findAll() {
//        return "end/adduser";
//    }
//
//    @GetMapping("/403")
//    public String Unauthorized(){
//        return "end/Unauthorized";
//    }

}
