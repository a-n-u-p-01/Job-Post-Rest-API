package com.zero.JobPostApp.Services;

import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.Entity.JobApplication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    List<Company> getAllCompany();
    Company getCompanyById(Long id);
    boolean addCompany(Company company);
    boolean updateCompany(Company company,Long id);
    boolean deleteCompany(Long id);
    void approveApplication(Long applicationId);
}
