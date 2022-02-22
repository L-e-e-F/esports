package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping("front/{page}")
    public String toFrontPage(@PathVariable String page) {
        return "front/"+page;
    }
    @RequestMapping("end/{page}")
    public String toEndPage(@PathVariable String page) {
        return "end/"+page;
    }
    @RequestMapping("Index")
    public String toIndex(){ return "index";}
    @RequestMapping("Test")
    public String toTest(){ return "test";}
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
