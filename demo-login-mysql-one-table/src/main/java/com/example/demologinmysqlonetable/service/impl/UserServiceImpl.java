package com.example.demologinmysqlonetable.service.impl;

import com.example.demologinmysqlonetable.entity.User;
import com.example.demologinmysqlonetable.repository.UserRepository;
import com.example.demologinmysqlonetable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
