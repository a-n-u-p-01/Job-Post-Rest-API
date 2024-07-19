package com.zero.JobPostApp.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    @ManyToOne
    private Company company;
}
