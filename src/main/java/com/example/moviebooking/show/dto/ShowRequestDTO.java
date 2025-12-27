package com.example.moviebooking.show.dto;


import com.example.moviebooking.common.enums.ShowStatus;
import com.example.moviebooking.theater.enums.SeatCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Data
public class ShowRequestDTO {

    private String movieId;
    private Long theatreId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate showDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime showTime;

    private Map<SeatCategory, Integer> totalSeats;
    private Map<SeatCategory, Double> pricePerSeat;

    private ShowStatus status;
}

