package com.zero.JobPostApp.Services;

import com.zero.JobPostApp.Entity.Job;
import com.zero.JobPostApp.Entity.JobApplication;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface JobService {
    List<Job> getAllJobs();
    boolean createJob(Job job, Long companyId);
    Job getJobById(Long id);
    void deleteJobById(Long id);
    boolean updateJob(Job job, Long id);
    List<JobApplication> getAllJobApplication(Long jobId);
}
