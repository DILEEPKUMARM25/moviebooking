package com.example.moviebooking.show.controller;

import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.show.dto.ShowRequestDTO;
import com.example.moviebooking.show.dto.ShowResponseDTO;
import com.example.moviebooking.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ResponseEntity<CommonRespose> create(@RequestBody ShowRequestDTO dto) {
        return ResponseEntity.ok(new CommonRespose(false,"Show Added", showService.createShow(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonRespose> getById(@PathVariable Long id) {
       // return ;
        return ResponseEntity.ok(new CommonRespose(false,"Get Show ById",showService.getShowById(id)));

    }

    @GetMapping
    public List<ShowResponseDTO> getAll() {
        return showService.getAllShows();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonRespose> update(
            @PathVariable Long id,
            @RequestBody ShowRequestDTO dto
    ) {
        return ResponseEntity.ok(new CommonRespose(false,"update Show ById",showService.updateShow(id, dto)));

      //  return ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonRespose> delete(@PathVariable Long id) {
        showService.deleteShow(id);
       // return "Show deleted successfully";
        return ResponseEntity.ok(new CommonRespose(false,"Delete show",showService.getShowById(id)));

    }
}

