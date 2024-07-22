package com.zero.JobPostApp.Services;

import com.zero.JobPostApp.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    void createUser(User user);
}
