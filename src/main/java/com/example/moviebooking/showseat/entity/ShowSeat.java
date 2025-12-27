package com.example.moviebooking.showseat.entity;

import com.example.moviebooking.theater.enums.SeatCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "show_seats",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"show_id", "seat_number"}
        )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "show_id", nullable = false)
    private Long showId;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;   // A1, A2, B3

    @Enumerated(EnumType.STRING)
    private SeatCategory seatCategory;

    private boolean booked;

    @Version
    private Long version; // 🔐 OPTIMISTIC LOCK
}

