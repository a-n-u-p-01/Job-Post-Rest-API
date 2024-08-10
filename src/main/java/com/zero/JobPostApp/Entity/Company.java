// This Java class represents a Company entity with fields like name, address, email, and industry type.
package com.zero.JobPostApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String manager;
    private String address;
    private String email;
    private String industryType;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;
    @JsonIgnore
    @OneToMany(mappedBy = "companyId")
    private List<Review> reviews;

}
