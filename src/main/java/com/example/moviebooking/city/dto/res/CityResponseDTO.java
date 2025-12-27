package com.example.moviebooking.city.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityResponseDTO {

    private Long id;
    private String name;

    private boolean popular;
}

