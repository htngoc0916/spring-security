package com.example.demologingoogle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/home")
    public String welcome(){
        return "Welcome to Google login";
    }
    @GetMapping("/user")
    public String user(Principal principal){
        System.out.println("username: " + principal.getName());
        return principal.getName();
    }

}
