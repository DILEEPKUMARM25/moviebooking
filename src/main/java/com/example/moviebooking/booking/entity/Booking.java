package com.example.moviebooking.booking.entity;


import com.example.moviebooking.common.enums.BookingStatus;
import com.example.moviebooking.theater.enums.SeatCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer userId;
    private Long showId;

    private String seatNumber; //  REQUIRED for cancel

    @Enumerated(EnumType.STRING)
    private SeatCategory seatCategory;

    private int quantity;
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
