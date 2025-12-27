package com.example.moviebooking.movie.entity;


import com.example.moviebooking.common.enums.Language;
import com.example.moviebooking.common.enums.MovieStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.angus.mail.iap.ByteArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private String id;

    private String title;
    private String description;

    private Double duration; // minutes
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovieStatus status;

    private byte[] poster;

    // getters & setters
}

