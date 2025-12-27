package com.example.moviebooking.movie.service;



import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.movie.dto.ReviewRequestDTO;
import com.example.moviebooking.movie.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {

    ReviewResponseDTO addReview(ReviewRequestDTO dto) throws DataNotFoundException;

    ReviewResponseDTO updateReview(String reviewId, ReviewRequestDTO dto) throws DataNotFoundException;

    void deleteReview(String reviewId);

    ReviewResponseDTO getReviewById(String reviewId) throws DataNotFoundException;

    List<ReviewResponseDTO> getReviewsByMovie(String movieId);
}
