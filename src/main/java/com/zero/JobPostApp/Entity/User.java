package com.zero.JobPostApp.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false,unique = true)
    private String password;
    private String about;
    private String skills;
    private String contact;
    private String resume;
    private List<String> roles;
    private Long companyId;
}
