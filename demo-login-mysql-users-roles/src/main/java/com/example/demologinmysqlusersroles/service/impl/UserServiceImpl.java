package com.example.demologinmysqlusersroles.service.impl;

import com.example.demologinmysqlusersroles.entity.User;
import com.example.demologinmysqlusersroles.repository.UserRepository;
import com.example.demologinmysqlusersroles.service.UserService;
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
