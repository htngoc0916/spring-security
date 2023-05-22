package com.example.demologinjwt.controller;

import com.example.demologinjwt.dto.UserRequest;
import com.example.demologinjwt.dto.UserResponse;
import com.example.demologinjwt.security.custom.CustomUserDetailsService;
import com.example.demologinjwt.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/home")
    public String home() {
        return "Welcome to ngochoccode!!";
    }

    @GetMapping("/test")
    public String test() {
        return "Welcome to test page!!";
    }

    @GetMapping("/user/home")
    public String userPage() {
        return "Welcome User htngoc!!";
    }

    @GetMapping("/admin/home")
    public String adminPage() {
        return "Welcome Admin!!";
    }

    @PostMapping("/login")
    public UserResponse authenticate(@RequestBody UserRequest userRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getUsername(),
                            userRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userRequest.getUsername());

        final String token = jwtProvider.generateToken(userDetails);

        return  new UserResponse(token);
    }
}
