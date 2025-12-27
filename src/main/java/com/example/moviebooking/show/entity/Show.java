package com.example.moviebooking.show.entity;


import com.example.moviebooking.theater.enums.SeatCategory;
import com.example.moviebooking.common.enums.ShowStatus;
import com.example.moviebooking.theater.entity.Theatre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Entity
@Table(
        name = "shows",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"movie_id", "theatre_id", "show_date", "show_time"}
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MongoDB Movie reference
    @Column(name = "movie_id", nullable = false)
    private String movieId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    private LocalDate showDate;
    private LocalTime showTime;

    @ElementCollection
    @CollectionTable(name = "show_total_seats")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<SeatCategory, Integer> totalSeats;

    @ElementCollection
    @CollectionTable(name = "show_available_seats")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<SeatCategory, Integer> availableSeats;

    @ElementCollection
    @CollectionTable(name = "show_price")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<SeatCategory, Double> pricePerSeat;

    @Enumerated(EnumType.STRING)
    private ShowStatus status;

    // getters & setters
}

