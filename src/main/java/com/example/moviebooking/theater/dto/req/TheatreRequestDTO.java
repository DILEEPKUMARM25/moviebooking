package com.example.moviebooking.theater.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class TheatreRequestDTO {

    private String name;
    private Long cityId;
    private LocationDTO location;
    private List<ScreenDTO> screens;
    private Boolean active;
}
