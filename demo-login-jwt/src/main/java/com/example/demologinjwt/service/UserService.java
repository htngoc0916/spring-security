package com.example.demologinjwt.service;

import com.example.demologinjwt.entity.User;

public interface UserService {
    User findByUserName(String userName);
}
