package com.zero.JobPostApp.Repository;

import com.zero.JobPostApp.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository  extends JpaRepository<Job,Long> {

}
