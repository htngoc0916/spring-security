package com.example.demologinmysqlusersroles.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/home")
    public String goHome(){
        return "This is Home page";
    }

    @GetMapping("/user")
    public String goUser(){
        return "This is User page";
    }

    @GetMapping("/admin")
    public String goAdmin(){
        return "This is Admin page";
    }
}
