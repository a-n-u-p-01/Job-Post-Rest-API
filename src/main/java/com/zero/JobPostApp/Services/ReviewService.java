package com.zero.JobPostApp.Services;
import com.zero.JobPostApp.Entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
       List<Review> getAllReviews();
       boolean createReview(Review review, Long userId, Long companyId);

       List<Review> getAllReviewsByCompany(Long companyId);
}
