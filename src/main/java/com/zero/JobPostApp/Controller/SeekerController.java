package com.zero.JobPostApp.Controller;

import com.zero.JobPostApp.Entity.JobApplication;
import com.zero.JobPostApp.Entity.User;
import com.zero.JobPostApp.Services.JobApplicationService;
import com.zero.JobPostApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seeker")
public class SeekerController {
    @Autowired
    private UserService userService;
    @Autowired
    private JobApplicationService jobApplicationService;

    //Get seeker data
    @GetMapping
    public ResponseEntity<User> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(userService.getUser(authentication.getName()),HttpStatus.OK);
    }

    //Create Application
    @PostMapping("/{userId}/{jobId}/{companyId}")
    public ResponseEntity<?> applyForJob(@PathVariable Long userId, @PathVariable Long jobId, @PathVariable Long companyId, @RequestBody JobApplication jobApplication){
        jobApplicationService.createJobApplication(userId,jobId,companyId,jobApplication);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
