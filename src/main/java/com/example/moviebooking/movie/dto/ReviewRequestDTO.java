package com.example.moviebooking.movie.dto;

import lombok.Data;

@Data
public class ReviewRequestDTO {

    private String movieId;
    private Integer userId;
    private int rating;
    private String comment;
}

