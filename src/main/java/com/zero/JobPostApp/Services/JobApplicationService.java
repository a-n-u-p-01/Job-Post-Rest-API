package com.zero.JobPostApp.Services;

import com.zero.JobPostApp.Entity.JobApplication;
import org.springframework.stereotype.Service;

@Service
public interface JobApplicationService {
    void createJobApplication(Long userId, Long jobId, Long companyId, JobApplication jobApplication);
}
