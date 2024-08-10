package com.zero.JobPostApp.ServiceImpl;

import com.zero.JobPostApp.Entity.JobApplication;
import com.zero.JobPostApp.Repository.JobApplicationRepository;
import com.zero.JobPostApp.Repository.JobRepository;
import com.zero.JobPostApp.Services.JobApplicationService;
import com.zero.JobPostApp.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private JobRepository jobRepository;
    @Override
    public void createJobApplication(Long userId, Long jobId, JobApplication jobApplication) {
        if(!jobRepository.findById(jobId).isPresent()) {
            throw new NoSuchElementException("Job Not found");
        }
        if(!jobApplicationRepository.existsByUserIdAndJobId(userId,jobId)){
        jobApplication.setApplicationStatus(false);
        jobApplication.setJobId(jobId);
        jobApplication.setUserId(userId);
        jobApplicationRepository.save(jobApplication);
        }
    }
}
