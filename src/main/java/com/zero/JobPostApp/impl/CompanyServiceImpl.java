package com.zero.JobPostApp.impl;

import com.zero.JobPostApp.Entity.Company;
import com.zero.JobPostApp.Repository.CompanyRepository;
import com.zero.JobPostApp.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addCompany(Company company) {
        if(company!=null){
        companyRepository.save(company);
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
        try {
        companyRepository.deleteById(id);
        return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
