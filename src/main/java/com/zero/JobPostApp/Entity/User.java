package com.zero.JobPostApp.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userName;
    private String about;
    private String contact;
    private String resume;
}
