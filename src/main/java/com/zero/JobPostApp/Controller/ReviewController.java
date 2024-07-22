package com.zero.JobPostApp.Controller;

import com.zero.JobPostApp.Entity.Review;
import com.zero.JobPostApp.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    //Get all Review
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(),HttpStatus.OK);
    }
    //Get All Review Associated with xyz company
    @GetMapping("/{companyId}")
    public ResponseEntity<List<Review>> getAllReviewsByCompany(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviewsByCompany(companyId),HttpStatus.OK);
    }
    //Create Review
    @PostMapping("/{userId}/{companyId}")
    public ResponseEntity<?> createReview(@RequestBody Review review,@PathVariable Long userId,@PathVariable Long companyId){
        if (reviewService.createReview(review,userId,companyId)){
        return new ResponseEntity<>("Review is added.",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("provide right credential",HttpStatus.BAD_REQUEST);
    }
}
