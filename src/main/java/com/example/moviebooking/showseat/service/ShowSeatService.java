package com.example.moviebooking.showseat.service;

import com.example.moviebooking.showseat.entity.ShowSeat;
import com.example.moviebooking.theater.enums.SeatCategory;

import java.util.List;

public interface ShowSeatService {

    void createSeatsForShow(
            Long showId,
            SeatCategory category,
            int seatCount,
            String rowPrefix
    );

    List<ShowSeat> getAvailableSeats(Long showId);
}

