package com.zero.JobPostApp.Controller;

import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyServiceImpl companyService;


    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany(){
        List<Company> companyList = companyService.getAllCompany();
        return new ResponseEntity<>(companyList, HttpStatus.FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        if(companyService.getCompanyById(id)!=null){
        return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody Company company){
        if(companyService.updateCompany(company,id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteCompany(@PathVariable Long id){
        return (companyService.deleteCompany(id) ?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
