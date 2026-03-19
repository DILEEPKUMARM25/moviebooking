package com.example.moviebooking.movie.service;

import com.example.moviebooking.movie.dto.MovieResponseDTO;

import java.util.List;

public interface PublicMovieService {
    List<MovieResponseDTO> getMoviesByCity(Long cityId);
}


