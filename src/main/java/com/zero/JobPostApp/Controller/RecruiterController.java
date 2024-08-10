package com.zero.JobPostApp.Controller;

import com.zero.JobPostApp.Entity.User;
import com.zero.JobPostApp.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    UserServiceImpl userService;

    //Get Recruiter Data 0
    @GetMapping
    public ResponseEntity<User> getRecruiter(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(userService.getUser(authentication.getName()), HttpStatus.OK);
    }

}
