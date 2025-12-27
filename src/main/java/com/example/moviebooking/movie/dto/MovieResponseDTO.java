package com.example.moviebooking.movie.dto;

import com.example.moviebooking.common.enums.Language;
import com.example.moviebooking.common.enums.MovieStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieResponseDTO {

    private String id;
    private String title;
    private String description;
    private Double duration;
    private Language language;
    private MovieStatus status;
}

