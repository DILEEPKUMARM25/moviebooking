package com.example.moviebooking.movie.controller;

import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.movie.service.PublicMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicMovieController {

    private final PublicMovieService publicMovieService;

    @GetMapping("/movies/by-city/{cityId}")
    public ResponseEntity<CommonRespose> getMoviesByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(
                new CommonRespose(false, "Movies by city fetched successfully",
                        publicMovieService.getMoviesByCity(cityId))
        );
    }
}
