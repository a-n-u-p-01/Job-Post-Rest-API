package com.zero.JobPostApp.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean applicationStatus;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long jobId;

    private String applicantName;
    private String jobRole;
    private String skills;
    private String experience;
}
