package com.zero.JobPostApp.Controller;

import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.Entity.Job;
import com.zero.JobPostApp.Entity.JobApplication;
import com.zero.JobPostApp.ServiceImpl.UserServiceImpl;
import com.zero.JobPostApp.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    private UserServiceImpl userService;


    //Get Company Data 0
    @GetMapping
    public ResponseEntity<Company> getCompanyData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = userService.getUser(authentication.getName()).getCompanyId();
        if(companyService.getCompanyById(id)!=null){
        return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //Create Company 0
    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return new ResponseEntity<>("Company is created.",HttpStatus.OK);
    }
    //Get All Jobs of company that have recruiter assigned 0
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = userService.getUser(authentication.getName()).getCompanyId();
        return new ResponseEntity<>(companyService.getCompanyById(id).getJobs(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody Company company){
        if(companyService.updateCompany(company,id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteCompany(@PathVariable Long id){
        return (companyService.deleteCompany(id) ?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("approve-form/{applicationId}")
    public ResponseEntity<?> approveApplication(@PathVariable Long applicationId){
        companyService.approveApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
