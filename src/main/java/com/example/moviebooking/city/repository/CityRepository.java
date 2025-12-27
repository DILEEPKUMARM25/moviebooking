package com.example.moviebooking.city.repository;

import com.example.moviebooking.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}

