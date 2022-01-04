package com.example.emaillogtime.service.impl;

import com.example.emaillogtime.reposiotry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements com.example.emaillogtime.service.UserService {
    @Autowired
    private UserRepository userRepository;
}
