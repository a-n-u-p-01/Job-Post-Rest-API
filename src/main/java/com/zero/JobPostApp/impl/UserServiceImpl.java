package com.zero.JobPostApp.impl;

import com.zero.JobPostApp.Entity.User;
import com.zero.JobPostApp.Repository.UserRepository;
import com.zero.JobPostApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    //Get All User
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Create User
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

}
