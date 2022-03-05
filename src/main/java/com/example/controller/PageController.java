package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String hello() {
        return "login";
    }

    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page) {
        System.out.println(page);
        return page;
    }
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
