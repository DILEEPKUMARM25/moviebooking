package com.example.moviebooking.show.repository;

import com.example.moviebooking.show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Long> {
    Optional<Object> findByMovieIdAndTheatre_IdAndShowDateAndShowTime(String movieId, Long theatreId, LocalDate showDate, LocalTime showTime);
}
