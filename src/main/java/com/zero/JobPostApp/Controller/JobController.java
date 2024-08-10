package com.zero.JobPostApp.Controller;
import com.zero.JobPostApp.Entity.Job;

import com.zero.JobPostApp.ServiceImpl.UserServiceImpl;
import com.zero.JobPostApp.Services.JobService;
import com.zero.JobPostApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;
    @Autowired
    private UserService userService;


    //GetAllJobs
    @GetMapping
    public List<Job>  getAllJobs(){
        return jobService.getAllJobs();
    }
    //GetJobById
    @GetMapping("{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        if(jobService.getJobById(id) !=null){
            return new ResponseEntity<>(jobService.getJobById(id),HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Create Job R 0
    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long companyId = userService.getUser(authentication.getName()).getCompanyId();
        if(jobService.createJob(job,companyId)){
        return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    //Update Job R
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@RequestBody Job job,@PathVariable Long id){
        if(jobService.updateJob(job,id)){
            return new ResponseEntity<>("Job is Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Delete Job r
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteJobById(@PathVariable Long id){
    if(jobService.getJobById(id)!= null){
        jobService.deleteJobById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
