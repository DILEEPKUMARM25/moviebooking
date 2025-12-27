package com.example.moviebooking.booking.service;

import com.example.moviebooking.booking.entity.Booking;
import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.theater.enums.SeatCategory;

public interface BookingService {

    Booking confirmBooking(
            Long userId,
            Long showId,
            String seatNumber,
            SeatCategory seatCategory
    ) throws DataNotFoundException;

    Booking cancelBooking(Long bookingId) throws DataNotFoundException;
}

