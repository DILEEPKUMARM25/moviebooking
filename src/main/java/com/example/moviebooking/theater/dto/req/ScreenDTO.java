package com.example.moviebooking.theater.dto.req;

import com.example.moviebooking.theater.enums.ScreenType;
import lombok.Data;

@Data
public class ScreenDTO {

    private String name;
    private ScreenType type;
    private SeatLayoutDTO seatLayout;
}
