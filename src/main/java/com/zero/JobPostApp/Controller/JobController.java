package com.zero.JobPostApp.Controller;
import com.zero.JobPostApp.Entity.Job;

import com.zero.JobPostApp.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;


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
    //get job by  company
    @GetMapping("company/{id}")
    public ResponseEntity<List<Job>> getJobByCompany(@PathVariable Long id){
        if(!jobService.getJobByCompany(id).isEmpty()){
            return new ResponseEntity<>(jobService.getJobByCompany(id),HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Create Job
    @PostMapping("/{companyId}")
    public ResponseEntity<?> createJob(@RequestBody Job job,@PathVariable Long companyId){
        if(jobService.createJob(job,companyId)){
        return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    //Update Job
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@RequestBody Job job,@PathVariable Long id){
        if(jobService.updateJob(job,id)){
            return new ResponseEntity<>("Job is Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Delete Job
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteJobById(@PathVariable Long id){
        if(jobService.deleteJobById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
