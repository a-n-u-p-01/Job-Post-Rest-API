package com.zero.JobPostApp.Controller;

import com.zero.JobPostApp.Entity.JobApplication;
import com.zero.JobPostApp.Entity.User;
import com.zero.JobPostApp.Services.JobApplicationService;
import com.zero.JobPostApp.Services.UserService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JobApplicationService jobApplicationService;
    //Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    //Create User
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User Created",HttpStatus.CREATED);
    }
    //Create Application
    @PostMapping("/{userId}/{jobId}/{companyId}")
    public ResponseEntity<?> applyForJob(@PathVariable Long userId, @PathVariable Long jobId, @PathVariable Long companyId, @RequestBody JobApplication jobApplication){
        jobApplicationService.createJobApplication(userId,jobId,companyId,jobApplication);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
