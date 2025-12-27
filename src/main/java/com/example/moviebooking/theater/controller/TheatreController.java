package com.example.moviebooking.theater.controller;



import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.theater.dto.req.TheatreRequestDTO;

import com.example.moviebooking.theater.service.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    // CREATE THEATRE
    @PostMapping
    public ResponseEntity<CommonRespose> createTheatre(
            @RequestBody TheatreRequestDTO dto) {
        return ResponseEntity.ok(new CommonRespose(false,"Theatre added",theatreService.createTheatre(dto)));

    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CommonRespose> getTheatre(@PathVariable Long id) {
       // return ;
        return ResponseEntity.ok(new CommonRespose(false,"fetch Theatre by id",theatreService.getTheatreById(id)));

    }

    // GET ALL
    @GetMapping
    public ResponseEntity<CommonRespose> getAllTheatres() {
      //  return ;
        return  ResponseEntity.ok(new CommonRespose(false,"Fetch All Theatre ",theatreService.getAllTheatres()));

    }

    // ADMIN APPROVE THEATRE
    @PatchMapping("/{id}/approve")
    public ResponseEntity<CommonRespose> approveTheatre(@PathVariable Long id) {
        theatreService.approveTheatre(id);

        return ResponseEntity.ok(new CommonRespose(false,"Theatre Approval successfully",null));

    }
}

