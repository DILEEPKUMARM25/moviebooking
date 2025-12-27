package com.example.moviebooking.movie.controller;

import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.common.resposeEntity.CommonRespose;
import com.example.moviebooking.movie.dto.MovieRequestDTO;
import com.example.moviebooking.movie.dto.MovieResponseDTO;
import com.example.moviebooking.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<CommonRespose> create(MovieRequestDTO dto) {
        return ResponseEntity.ok(new CommonRespose(false,"Add movie",movieService.create(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonRespose> get(@PathVariable String id) {
        return ResponseEntity.ok(new CommonRespose(false,"Get movie by id",movieService.getById(id)));
    }
    @GetMapping("/poster/{id}")
    public ResponseEntity<byte[]> getPoster(@PathVariable String id) throws DataNotFoundException {

        byte[] imageBytes = movieService.getPoster(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG
                .body(imageBytes);
    }


    @GetMapping
    public List<MovieResponseDTO> getAll() {


        return movieService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonRespose> update(
            @PathVariable String id,
            @RequestBody MovieRequestDTO dto) throws DataNotFoundException {
        return ResponseEntity.ok(new CommonRespose(false,"Movie update",movieService.update(id, dto)));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonRespose> delete(@PathVariable String id) {
        movieService.delete(id);
        return ResponseEntity.ok(new CommonRespose(false,"Movie delete",null));


    }
}
