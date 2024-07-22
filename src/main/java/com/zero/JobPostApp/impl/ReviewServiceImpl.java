package com.zero.JobPostApp.impl;

import com.zero.JobPostApp.Entity.Review;
import com.zero.JobPostApp.Repository.CompanyRepository;
import com.zero.JobPostApp.Repository.ReviewRepository;
import com.zero.JobPostApp.Repository.UserRepository;
import com.zero.JobPostApp.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    //Create Review
    @Override
    public boolean createReview(Review review, Long userId, Long companyId) {
       if(companyRepository.findById(companyId).isPresent() && userRepository.findById(userId).isPresent()){
            review.setCompanyId(companyId);
            review.setUserId(userId);
            reviewRepository.save(review);
            return true;
       }
       return false;
    }
//Get All Reviews By Company
    @Override
    public List<Review> getAllReviewsByCompany(Long companyId) {
        return  reviewRepository.findAll().stream()
                .filter(review -> review.getCompanyId().equals(companyId))
                .collect(Collectors.toList());

    }
}
