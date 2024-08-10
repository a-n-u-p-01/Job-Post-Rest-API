package com.zero.JobPostApp.ServiceImpl;

import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.Entity.JobApplication;
import com.zero.JobPostApp.Entity.User;
import com.zero.JobPostApp.Repository.CompanyRepository;
import com.zero.JobPostApp.Repository.JobApplicationRepository;
import com.zero.JobPostApp.Repository.JobRepository;
import com.zero.JobPostApp.Repository.UserRepository;
import com.zero.JobPostApp.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;



    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return companyRepository.findById(id).orElse(null);
    }



    @Override
    public boolean addCompany(Company company) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(authentication.getName());
        if(company!=null){
            company.setManager(user.getUserName());
            Company company1 = companyRepository.save(company);
            user.setCompanyId(company1.getId());
            userRepository.save(user);
//   ***  userRepository.save(user.setCompanyId(company1.getId())); // error because user.setCompanyId(company1.getId()) it return void
        return true;
        }
        return false;
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            company.setId(id);
            companyRepository.save(company); //here if id == table id then jpa do not save new entry instead update matched id entry
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
      if(getCompanyById(id) == null) return false;
      jobRepository.findAll().stream().filter(job -> job.getCompany().getId().equals(id)).forEach(job -> jobRepository.deleteById(job.getId()));
      companyRepository.deleteById(id);
      return true;
    }


    @Override
    public void approveApplication(Long applicationId) {
        Optional<JobApplication> optionalJobApplication = jobApplicationRepository.findById(applicationId);
        if(optionalJobApplication.isPresent()){
         JobApplication jobApplication =  optionalJobApplication.get();
         jobApplication.setApplicationStatus(true);
         jobApplicationRepository.save(jobApplication);
        }

    }

}
