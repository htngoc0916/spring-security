package com.example.demologinjwt.service.impl;

import com.example.demologinjwt.entity.User;
import com.example.demologinjwt.repository.UserRepository;
import com.example.demologinjwt.service.UserService;
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
