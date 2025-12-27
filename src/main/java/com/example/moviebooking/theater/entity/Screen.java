package com.example.moviebooking.theater.entity;
import com.example.moviebooking.theater.enums.ScreenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "screens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ScreenType type;

    private int totalSeats;

    @Embedded
    private SeatLayout seatLayout;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
}
