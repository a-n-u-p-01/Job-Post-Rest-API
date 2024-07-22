package com.zero.JobPostApp.impl;

import com.zero.JobPostApp.Entity.JobApplication;
import com.zero.JobPostApp.Repository.JobApplicationRepository;
import com.zero.JobPostApp.Services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Override
    public void createJobApplication(Long userId, Long jobId, Long companyId, JobApplication jobApplication) {
        if(!jobApplicationRepository.existsByUserIdAndJobId(userId,jobId)){
        jobApplication.setApplicationStatus(false);
        jobApplication.setCompanyId(companyId);
        jobApplication.setJobId(jobId);
        jobApplication.setUserId(userId);
        jobApplicationRepository.save(jobApplication);
        }
    }
}
