package com.example.moviebooking.movie.dto;


import com.example.moviebooking.common.enums.Language;
import com.example.moviebooking.common.enums.MovieStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MovieRequestDTO {

    private String title;
    private String description;
    private Double duration;
    private Language language;
    private MovieStatus status;
    private MultipartFile poster;
}

