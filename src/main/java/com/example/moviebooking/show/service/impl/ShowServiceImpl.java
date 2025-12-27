package com.example.moviebooking.show.service.impl;


import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.show.dto.ShowRequestDTO;
import com.example.moviebooking.show.dto.ShowResponseDTO;
import com.example.moviebooking.show.entity.Show;

import com.example.moviebooking.show.repository.ShowRepository;
import com.example.moviebooking.show.service.ShowService;
import com.example.moviebooking.theater.entity.Theatre;
import com.example.moviebooking.theater.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final TheatreRepository theatreRepository;

    @Override
    public ShowResponseDTO createShow(ShowRequestDTO dto) {

        Theatre theatre = theatreRepository.findById(dto.getTheatreId())
                .orElseThrow(() -> new DataNotFoundException("Theatre not found"));

        // Check duplicate show
        showRepository.findByMovieIdAndTheatre_IdAndShowDateAndShowTime(
                dto.getMovieId(),
                dto.getTheatreId(),
                dto.getShowDate(),
                dto.getShowTime()
        ).ifPresent(s -> {
            throw new DataNotFoundException("Show already exists");
        });

        Show show = new Show();
        show.setMovieId(dto.getMovieId());
        show.setTheatre(theatre);
        show.setShowDate(dto.getShowDate());
        show.setShowTime(dto.getShowTime());
        show.setTotalSeats(dto.getTotalSeats());
        show.setAvailableSeats(dto.getTotalSeats()); // initially same
        show.setPricePerSeat(dto.getPricePerSeat());
        show.setStatus(dto.getStatus());

        return toResponse(showRepository.save(show));
    }

    @Override
    public ShowResponseDTO getShowById(Long id) {
        return toResponse(
                showRepository.findById(id)
                        .orElseThrow(() -> new DataNotFoundException("Show not found"))
        );
    }

    @Override
    public List<ShowResponseDTO> getAllShows() {
        return showRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ShowResponseDTO updateShow(Long id, ShowRequestDTO dto) {

        Show show = showRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Show not found"));

        show.setShowDate(dto.getShowDate());
        show.setShowTime(dto.getShowTime());
        show.setTotalSeats(dto.getTotalSeats());
        show.setPricePerSeat(dto.getPricePerSeat());
        show.setStatus(dto.getStatus());

        return toResponse(showRepository.save(show));
    }

    @Override
    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }

    private ShowResponseDTO toResponse(Show show) {
        return new ShowResponseDTO(
                show.getId(),
                show.getMovieId(),
                show.getTheatre().getId(),
                show.getShowDate(),
                show.getShowTime(),
                show.getTotalSeats(),
                show.getAvailableSeats(),
                show.getPricePerSeat(),
                show.getStatus()
        );
    }
}

