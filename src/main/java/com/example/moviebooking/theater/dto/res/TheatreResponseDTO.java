package com.example.moviebooking.theater.dto.res;

import com.example.moviebooking.theater.dto.req.LocationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TheatreResponseDTO {

    private Long id;
    private String name;
    private String cityName;
    private LocationDTO location;
    private boolean active;
}

