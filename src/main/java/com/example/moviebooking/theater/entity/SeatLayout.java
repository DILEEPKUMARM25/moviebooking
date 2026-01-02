package com.example.moviebooking.theater.entity;
import com.example.moviebooking.theater.enums.SeatCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatLayout {

    /**
     * Example:
     * SILVER -> 50 seats
     * GOLD -> 30 seats
     * RECLINER -> 10 seats
     */
    @ElementCollection
    @CollectionTable(name = "screen_seats", joinColumns = @JoinColumn(name = "screen_id"))
    @MapKeyEnumerated(EnumType.STRING)   //  VERY IMPORTANT
    @Column(name = "seats")
    private Map<SeatCategory, Integer> seatsPerCategory;

    // getters & setters
}

