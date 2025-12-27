package com.example.moviebooking.city.dto.req;


import lombok.Data;

@Data
public class CityRequestDTO {

    private String name;

    private Boolean enabled;
    private Boolean popular;
    private Integer displayOrder;
}

