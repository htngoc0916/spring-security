package com.example.demologinmysqlonetable.service;

import com.example.demologinmysqlonetable.entity.User;

public interface UserService {
    User findByUserName(String userName);
}
