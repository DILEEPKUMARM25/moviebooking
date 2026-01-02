package com.example.moviebooking.booking.service.impl;

import com.example.moviebooking.booking.entity.Booking;
import com.example.moviebooking.booking.repository.BookingRepository;
import com.example.moviebooking.booking.service.BookingService;
import com.example.moviebooking.common.enums.BookingStatus;
import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.notification.EmailService;
import com.example.moviebooking.showseat.entity.ShowSeat;
import com.example.moviebooking.showseat.repository.ShowSeatRepository;
import com.example.moviebooking.theater.enums.SeatCategory;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

@Autowired
    private ShowSeatRepository showSeatRepository;

@Autowired
    private  BookingRepository bookingRepository;

@Autowired
    private EmailService emailService;

    @Override
    public Booking confirmBooking(
            Long userId,
            Long showId,
            String seatNumber,
            SeatCategory seatCategory)  {

        ShowSeat seat = showSeatRepository
                .findByShowIdAndSeatNumber(showId, seatNumber)
                .orElseThrow(() -> new DataNotFoundException("Seat not found"));

        if (seat.isBooked()) {
            throw new DataNotFoundException("Seat already booked");
        }

        //  OPTIMISTIC LOCK HERE
        seat.setBooked(true);
        showSeatRepository.save(seat);

        Booking booking = new Booking();
        booking.setUserId(userId.intValue());
        booking.setShowId(showId);
        booking.setSeatNumber(seatNumber);
        booking.setSeatCategory(seatCategory);
        booking.setQuantity(1);
        booking.setTotalAmount(200);
        booking.setStatus(BookingStatus.CONFIRMED);

        Booking saved = bookingRepository.save(booking);

        emailService.sendBookingConfirmation(userId, saved);

        return saved;
    }

    @Override
    public Booking cancelBooking(Long bookingId)  {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new DataNotFoundException("Booking already cancelled");
        }

        //  Release seat
        ShowSeat seat = showSeatRepository
                .findByShowIdAndSeatNumber(
                        booking.getShowId(),
                        booking.getSeatNumber())
                .orElseThrow(() -> new DataNotFoundException("Seat not found"));

        seat.setBooked(false);
        showSeatRepository.save(seat);


        booking.setStatus(BookingStatus.CANCELLED);
        Booking cancelledBooking = bookingRepository.save(booking);


        emailService.sendBookingCancellation(
                booking.getUserId().longValue(),
                cancelledBooking
        );

        return cancelledBooking;
    }

}

