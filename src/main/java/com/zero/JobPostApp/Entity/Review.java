package com.zero.JobPostApp.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @Max(10)
    @Min(0)
    @Column(nullable = false)
    private int stars;
    @Column(nullable = false)
    private Long companyId;
   @Column(nullable = false)
    private Long userId;

}

