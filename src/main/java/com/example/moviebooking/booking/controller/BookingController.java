package com.example.moviebooking.booking.controller;

import com.example.moviebooking.booking.service.BookingService;
import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.theater.enums.SeatCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService; // INTERFACE

    @PostMapping("/confirm")
    public ResponseEntity<CommonRespose> bookSeat(
            @RequestParam Long userId,
            @RequestParam Long showId,
            @RequestParam String seatNumber,
            @RequestParam SeatCategory seatCategory) {

        return ResponseEntity.ok(
                new CommonRespose(false,"booking",bookingService.confirmBooking(
                        userId, showId, seatNumber, seatCategory)));
    }

    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<CommonRespose> cancelBooking(
            @PathVariable Long bookingId) {

        return ResponseEntity.ok(
                new CommonRespose(false,"Booking update",bookingService.cancelBooking(bookingId)));
    }
}
