package com.example.demologinmysqlonetable.security;

import com.example.demologinmysqlonetable.dto.UserDTO;
import com.example.demologinmysqlonetable.entity.User;
import com.example.demologinmysqlonetable.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not Found : " + username);
        }

        return new CustomUserDetails(user);
    }
}
