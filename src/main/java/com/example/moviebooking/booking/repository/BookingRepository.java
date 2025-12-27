package com.example.moviebooking.booking.repository;

import com.example.moviebooking.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {
}
