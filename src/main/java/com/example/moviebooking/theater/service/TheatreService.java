package com.example.moviebooking.theater.service;





import com.example.moviebooking.theater.dto.req.TheatreRequestDTO;
import com.example.moviebooking.theater.dto.res.TheatreResponseDTO;

import java.util.List;

public interface TheatreService {

    TheatreResponseDTO createTheatre(TheatreRequestDTO dto);

    TheatreResponseDTO getTheatreById(Long id);

    List<TheatreResponseDTO> getAllTheatres();

    void approveTheatre(Long id);
}
