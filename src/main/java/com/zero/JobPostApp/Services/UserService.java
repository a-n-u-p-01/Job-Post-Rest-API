package com.zero.JobPostApp.Services;

import com.zero.JobPostApp.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User getUser(String userName);
    List<User> getAllUsers();
    void createUser(User user);
    void createRecruiter(User user);
}
