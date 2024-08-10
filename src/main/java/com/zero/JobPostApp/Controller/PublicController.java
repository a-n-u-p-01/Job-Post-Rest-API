package com.zero.JobPostApp.Controller;

import com.zero.JobPostApp.Entity.User;
import com.zero.JobPostApp.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserServiceImpl userService;
    //health 0
    @GetMapping
    public String healthCheck(){
        return "Ok";
    }

    //Create seeker 0
    @PostMapping("/create-seeker")
    public ResponseEntity<?> createSeeker(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User Seeker Created", HttpStatus.CREATED);
    }

    //Create recruiter 0
    @PostMapping("/create-recruiter")
    public ResponseEntity<?> createRecruiter(@RequestBody User user){
        userService.createRecruiter(user);
        return new ResponseEntity<>("Recruiter Created", HttpStatus.CREATED);
    }

}
