package com.example.moviebooking.showseat.service.impl;

import com.example.moviebooking.showseat.entity.ShowSeat;
import com.example.moviebooking.showseat.repository.ShowSeatRepository;
import com.example.moviebooking.showseat.service.ShowSeatService;
import com.example.moviebooking.theater.enums.SeatCategory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ShowSeatServiceImpl implements ShowSeatService {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Override
    public void createSeatsForShow(
            Long showId,
            SeatCategory category,
            int seatCount,
            String rowPrefix) {

        for (int i = 1; i <= seatCount; i++) {

            String seatNumber = rowPrefix + i;

            // prevent duplicate seat creation
            boolean exists = showSeatRepository
                    .findByShowIdAndSeatNumber(showId, seatNumber)
                    .isPresent();

            if (!exists) {
                ShowSeat seat = new ShowSeat();
                seat.setShowId(showId);
                seat.setSeatNumber(seatNumber);
                seat.setSeatCategory(category);
                seat.setBooked(false);

                showSeatRepository.save(seat);
            }
        }
    }

    @Override
    public List<ShowSeat> getAvailableSeats(Long showId) {
        return showSeatRepository.findByShowId(showId)
                .stream()
                .filter(seat -> !seat.isBooked())
                .toList();
    }
}

