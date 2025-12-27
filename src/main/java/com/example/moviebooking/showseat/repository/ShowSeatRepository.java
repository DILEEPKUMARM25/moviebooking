package com.example.moviebooking.showseat.repository;

import com.example.moviebooking.showseat.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository
        extends JpaRepository<ShowSeat, Long> {

    Optional<ShowSeat> findByShowIdAndSeatNumber(
            Long showId, String seatNumber);

    List<ShowSeat> findByShowId(Long showId);
}


