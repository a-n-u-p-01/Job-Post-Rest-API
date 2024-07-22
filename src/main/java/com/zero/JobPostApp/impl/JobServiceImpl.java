package com.zero.JobPostApp.impl;
import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.Entity.Job;
import com.zero.JobPostApp.Repository.CompanyRepository;
import com.zero.JobPostApp.Repository.JobRepository;
import com.zero.JobPostApp.Services.JobService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyRepository companyRepository;


    //Get All jobs
    @Override
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    //Get By Id
    @Override
    public Job getJobById(Long id){
        return jobRepository.findById(id).orElse(null);
    }
    //get by company
    @Override
    public List<Job> getJobByCompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            return optionalCompany.get().getJobs();
        }
        return Collections.emptyList();
    }


    //delete by id
    @Override
    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public boolean updateJob(Job job,Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isPresent()){
            job.setId(id);
            jobRepository.save(job); //serialization// Id matched do not create new entry instead update existed one
            return true;
        }
        return false;
    }


    //Create job
    @Override
    public boolean createJob(Job job, Long companyId){
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if( optionalCompany.isPresent() && job!=null){
            job.setCompany(optionalCompany.get());
            jobRepository.save(job);
            optionalCompany.get().getJobs().add(job);
            companyRepository.save(optionalCompany.get());
            return true;
        }
        return false;
    }
}
