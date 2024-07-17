package com.zero.JobPostApp.Services;

import com.zero.JobPostApp.Entity.Job;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface JobService {
    List<Job> getAllJobs();
    boolean createJob(Job job, Long companyId);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Job job, Long id);
}
