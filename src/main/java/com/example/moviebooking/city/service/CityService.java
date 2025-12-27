package com.example.moviebooking.city.service;



import com.example.moviebooking.city.dto.req.CityRequestDTO;
import com.example.moviebooking.city.dto.res.CityResponseDTO;
import com.example.moviebooking.city.entity.City;
import com.example.moviebooking.common.exception.DataNotFoundException;

import java.util.List;

public interface CityService {

    // ADMIN
    City addCity(CityRequestDTO dto);
    City updateCity(Long id, CityRequestDTO dto) throws DataNotFoundException;
    void deleteCity(Long id) throws DataNotFoundException;
    City getCityById(Long id) throws DataNotFoundException;
    List<City> getAllCities();

    // USER
    List<CityResponseDTO> getEnabledCities();
    List<CityResponseDTO> getPopularCities();
}

