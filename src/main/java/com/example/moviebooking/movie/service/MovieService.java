package com.example.moviebooking.movie.service;

import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.movie.dto.MovieRequestDTO;
import com.example.moviebooking.movie.dto.MovieResponseDTO;

import java.util.List;

public interface MovieService {

    MovieResponseDTO create(MovieRequestDTO dto);
    MovieResponseDTO update(String id, MovieRequestDTO dto) throws DataNotFoundException;
    void delete(String id);
    MovieResponseDTO getById(String id) throws DataNotFoundException;
    List<MovieResponseDTO> getAll();

    byte[]  getPoster(String id) throws DataNotFoundException;
}
