package com.example.moviebooking.movie.service.impl;


import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.movie.dto.ReviewRequestDTO;
import com.example.moviebooking.movie.dto.ReviewResponseDTO;
import com.example.moviebooking.movie.entity.Review;
import com.example.moviebooking.movie.repository.MovieRepository;
import com.example.moviebooking.movie.repository.ReviewRepository;
import com.example.moviebooking.movie.service.ReviewService;
import com.example.moviebooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Override
    public ReviewResponseDTO addReview(ReviewRequestDTO dto) {
        movieRepository.findById(dto.getMovieId()).orElseThrow(()->new DataNotFoundException("movie not found"));

        userRepository.findById(dto.getUserId()).orElseThrow(()->new DataNotFoundException("user not found"));
        Review review = new Review();
        review.setMovieId(dto.getMovieId());
        review.setUserId(dto.getUserId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setCreatedAt(LocalDateTime.now());

        return toResponse(reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDTO updateReview(String reviewId, ReviewRequestDTO dto)  {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new DataNotFoundException("Review not found"));

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        return toResponse(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewResponseDTO getReviewById(String reviewId) {
        return reviewRepository.findById(reviewId)
                .map(this::toResponse)
                .orElseThrow(() -> new DataNotFoundException("Review not found"));
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByMovie(String movieId) {
        return reviewRepository.findByMovieId(movieId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ReviewResponseDTO toResponse(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getMovieId(),
                review.getUserId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }
}

