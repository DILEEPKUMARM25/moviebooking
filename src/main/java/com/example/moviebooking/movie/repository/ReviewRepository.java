package com.example.moviebooking.movie.repository;

import com.example.moviebooking.movie.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> findByMovieId(String movieId);
}
