package com.example.moviebooking.movie.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewResponseDTO {

    private String id;
    private String movieId;
    private Integer userId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}

