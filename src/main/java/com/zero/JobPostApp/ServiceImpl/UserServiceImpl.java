package com.zero.JobPostApp.ServiceImpl;

import com.zero.JobPostApp.Entity.User;
import com.zero.JobPostApp.Repository.UserRepository;
import com.zero.JobPostApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //Get User Data
    @Override
    public User getUser(String userName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUserName(authentication.getName());
    }

    //Get All User
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Create User
    @Override
    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("SEEKER"));
        userRepository.save(user);
    }

    //Create User
    @Override
    public void createRecruiter(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("RECRUITER"));
        userRepository.save(user);
    }


}
