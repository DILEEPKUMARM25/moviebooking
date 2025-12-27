package com.example.moviebooking.city.service;



import com.example.moviebooking.city.dto.req.CityRequestDTO;
import com.example.moviebooking.city.dto.res.CityResponseDTO;
import com.example.moviebooking.city.entity.City;
import com.example.moviebooking.city.repository.CityRepository;
import com.example.moviebooking.city.service.CityService;
import com.example.moviebooking.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    // ---------------- ADMIN ----------------

    @Override
    public City addCity(CityRequestDTO dto) {
        City city = new City();
        applyDto(city, dto);
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(Long id, CityRequestDTO dto)  {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("City not found"));
        applyDto(city, dto);
        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(Long id)  {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("City not found"));
        cityRepository.delete(city);
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("City not found"));
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // ---------------- USER ----------------

    @Override
    public List<CityResponseDTO> getEnabledCities() {
        return cityRepository.findAll()
                .stream()
                .filter(City::isEnabled)
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public List<CityResponseDTO> getPopularCities() {
        return cityRepository.findAll()
                .stream()
                .filter(city -> city.isEnabled() && city.isPopular())
                .map(this::toResponseDto)
                .toList();
    }

    // ---------------- HELPERS ----------------

    private void applyDto(City city, CityRequestDTO dto) {

        if (dto.getName() != null)
            city.setName(dto.getName());


        if (dto.getEnabled() != null)
            city.setEnabled(dto.getEnabled());

        if (dto.getPopular() != null)
            city.setPopular(dto.getPopular());

        if (dto.getDisplayOrder() != null)
            city.setDisplayOrder(dto.getDisplayOrder());
    }

    private CityResponseDTO toResponseDto(City city) {
        return new CityResponseDTO(
                city.getId(),
                city.getName(),
                city.isPopular()
        );
    }
}

