package com.example.moviebooking.movie.service.impl;

import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.movie.dto.MovieRequestDTO;
import com.example.moviebooking.movie.dto.MovieResponseDTO;
import com.example.moviebooking.movie.entity.Movie;
import com.example.moviebooking.movie.repository.MovieRepository;
import com.example.moviebooking.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieResponseDTO create(MovieRequestDTO dto) {
        Movie movie = toEntity(dto);
        if(dto.getPoster()!=null && !dto.getPoster().isEmpty()){
            try {
                movie.setPoster(dto.getPoster().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return toResponse(movieRepository.save(movie));
    }

    @Override
    public MovieResponseDTO update(String id, MovieRequestDTO dto)  {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));

        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setDuration(dto.getDuration());
        movie.setLanguage(dto.getLanguage());
        movie.setStatus(dto.getStatus());
        try {
            movie.setPoster(dto.getPoster().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return toResponse(movieRepository.save(movie));
    }

    @Override
    public void delete(String id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieResponseDTO getById(String id)  {
        return movieRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));
    }

    @Override
    public List<MovieResponseDTO> getAll() {
        return movieRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public byte[] getPoster(String movieId)  {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));

        if (movie.getPoster() == null) {
            throw new DataNotFoundException("Poster not available");
        }

        return movie.getPoster();
    }


    private Movie toEntity(MovieRequestDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setDuration(dto.getDuration());
        movie.setLanguage(dto.getLanguage());
        movie.setStatus(dto.getStatus());
        return movie;
    }

    private MovieResponseDTO toResponse(Movie movie) {
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getDuration(),
                movie.getLanguage(),
                movie.getStatus()
        );
    }
}

