package com.example.moviebooking.movie.service.impl;

import com.example.moviebooking.movie.dto.MovieResponseDTO;
import com.example.moviebooking.movie.entity.Movie;
import com.example.moviebooking.movie.repository.MovieRepository;
import com.example.moviebooking.movie.service.PublicMovieService;
import com.example.moviebooking.show.entity.Show;
import com.example.moviebooking.show.repository.ShowRepository;
import com.example.moviebooking.theater.entity.Theatre;
import com.example.moviebooking.theater.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicMovieServiceImpl implements PublicMovieService {

    private final TheatreRepository theatreRepository;
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;

    public PublicMovieServiceImpl(
            TheatreRepository theatreRepository,
            ShowRepository showRepository,
            MovieRepository movieRepository
    ) {
        this.theatreRepository = theatreRepository;
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieResponseDTO> getMoviesByCity(Long cityId) {
        List<Theatre> theatres = theatreRepository.findByCityId(cityId);

        List<Long> theatreIds = theatres.stream()
                .map(Theatre::getId)
                .distinct()
                .collect(Collectors.toList());

        if (theatreIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<Show> shows = showRepository.findByTheatreIdIn(theatreIds);

        List<String> movieIds = shows.stream()
                .map(show -> String.valueOf(show.getMovieId()))
                .distinct()
                .collect(Collectors.toList());

        if (movieIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<Movie> movies = movieRepository.findAllById(movieIds);

        return movies.stream()
                .map(movie -> new MovieResponseDTO(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getDescription(),
                        String.valueOf(movie.getLanguage()),
                        String.valueOf(movie.getStatus()),
                        movie.getDuration(),
                        "/movies/poster/" + movie.getId()
                ))
                .collect(Collectors.toList());
    }
}
