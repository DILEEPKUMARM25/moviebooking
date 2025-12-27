package com.example.moviebooking.theater.dto.req;

import com.example.moviebooking.theater.enums.SeatCategory;
import lombok.Data;

import java.util.Map;

@Data
public class SeatLayoutDTO {

    private Map<SeatCategory, Integer> seatsPerCategory;
}
