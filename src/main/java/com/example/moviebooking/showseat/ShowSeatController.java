package com.example.moviebooking.showseat;

import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.showseat.service.ShowSeatService;
import com.example.moviebooking.theater.enums.SeatCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shows")
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    //  ADMIN: CREATE SEATS
    @PostMapping("/{showId}/seats")
    public ResponseEntity<CommonRespose> createSeats(
            @PathVariable Long showId,
            @RequestParam SeatCategory seatCategory,
            @RequestParam int seatCount,
            @RequestParam String rowPrefix) {

        showSeatService.createSeatsForShow(
                showId, seatCategory, seatCount, rowPrefix);

        return ResponseEntity.ok(new CommonRespose(false,"Seats created successfully",null));
    }

    // USER: VIEW AVAILABLE SEATS
    @GetMapping("/{showId}/seats")
    public ResponseEntity<CommonRespose> getSeats(
            @PathVariable Long showId) {

        return ResponseEntity.ok( new CommonRespose(false,"list Shows",
                showSeatService.getAvailableSeats(showId)));
    }
}

