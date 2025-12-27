package com.example.moviebooking.movie.entity;



import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    private String id;
    private String movieId;   // Mongo Movie
    private Integer userId;      // MySQL User
    private int rating;       // 1–5
    private String comment;
    private LocalDateTime createdAt;

}

