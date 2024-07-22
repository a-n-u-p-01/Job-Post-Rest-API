package com.zero.JobPostApp.Repository;

import com.zero.JobPostApp.Entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    boolean existsByUserIdAndJobId(Long userId, Long jobId);
}
