package com.example.moviebooking.show.dto;



import com.example.moviebooking.common.enums.ShowStatus;
import com.example.moviebooking.theater.enums.SeatCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ShowResponseDTO {

    private Long id;
    private String movieId;
    private Long theatreId;

    private LocalDate showDate;
    private LocalTime showTime;

    private Map<SeatCategory, Integer> totalSeats;
    private Map<SeatCategory, Integer> availableSeats;
    private Map<SeatCategory, Double> pricePerSeat;

    private ShowStatus status;
}

