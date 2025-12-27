package com.example.moviebooking.show.service;

import com.example.moviebooking.show.dto.ShowRequestDTO;
import com.example.moviebooking.show.dto.ShowResponseDTO;

import java.util.List;

public interface ShowService {

    ShowResponseDTO createShow(ShowRequestDTO dto);

    ShowResponseDTO getShowById(Long id);

    List<ShowResponseDTO> getAllShows();

    ShowResponseDTO updateShow(Long id, ShowRequestDTO dto);

    void deleteShow(Long id);
}

