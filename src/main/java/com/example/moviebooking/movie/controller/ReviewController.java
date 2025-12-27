package com.example.moviebooking.movie.controller;



import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.movie.dto.ReviewRequestDTO;
import com.example.moviebooking.movie.dto.ReviewResponseDTO;
import com.example.moviebooking.movie.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping
    public ResponseEntity<CommonRespose> addReview(
            @RequestBody ReviewRequestDTO dto) {

        return ResponseEntity.ok(new CommonRespose(false,"Add review",reviewService.addReview(dto)));
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<CommonRespose> updateReview(
            @PathVariable String reviewId,
            @RequestBody ReviewRequestDTO dto) {
       // return ;
        return ResponseEntity.ok(new CommonRespose(false,"review update",reviewService.updateReview(reviewId, dto)));

    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<CommonRespose> deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(new CommonRespose(false,"review delete",null));

    }


    @GetMapping("/{reviewId}")
    public ResponseEntity<CommonRespose> getReviewById(
            @PathVariable String reviewId) {
        //return ;
        return ResponseEntity.ok(new CommonRespose(false,"Review Getting By Id",reviewService.getReviewById(reviewId)));

    }


    @GetMapping("/movie/{movieId}")
    public List<ReviewResponseDTO> getReviewsByMovie(
            @PathVariable String movieId) {
        return reviewService.getReviewsByMovie(movieId);

    }
}

