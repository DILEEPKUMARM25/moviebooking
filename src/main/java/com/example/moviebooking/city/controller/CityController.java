package com.example.moviebooking.city.controller;

import com.example.moviebooking.city.dto.req.CityRequestDTO;
import com.example.moviebooking.city.service.CityService;
import com.example.moviebooking.common.resposeEntity.CommonRespose;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    //  ADD
    @PostMapping
    public ResponseEntity<CommonRespose> addCity(@RequestBody CityRequestDTO dto) {
        return ResponseEntity.ok(new CommonRespose(false, "add location",cityService.addCity(dto)));
    }

    //  UPDATE (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<CommonRespose> updateCity(
            @PathVariable Long id,
            @RequestBody CityRequestDTO dto) {

        return ResponseEntity.ok(new CommonRespose(false,"update location",cityService.updateCity(id, dto)));
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonRespose> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok(new CommonRespose(false,"delete location",null));
    }

    //  FETCH BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CommonRespose> getCity(@PathVariable Long id) {
        return ResponseEntity.ok(new CommonRespose(false,"location Fetch",  cityService.getCityById( id)) );
    }

    // FETCH ALL
    @GetMapping("/all")
    public ResponseEntity<CommonRespose> getAllCities() {
        return ResponseEntity.ok(new CommonRespose(false,"Get all city",cityService.getAllCities()));
    }
}
