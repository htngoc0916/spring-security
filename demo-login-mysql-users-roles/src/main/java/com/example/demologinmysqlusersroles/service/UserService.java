package com.example.demologinmysqlusersroles.service;

import com.example.demologinmysqlusersroles.entity.User;
import com.example.demologinmysqlusersroles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {
    User findByUserName(String userName);
}
