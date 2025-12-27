package com.example.moviebooking.movie.repository;

import com.example.moviebooking.movie.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository  extends MongoRepository<Movie,String> {
}
