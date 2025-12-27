package com.example.moviebooking.theater.service.impl;




import com.example.moviebooking.city.entity.City;
import com.example.moviebooking.city.repository.CityRepository;
import com.example.moviebooking.theater.dto.req.LocationDTO;
import com.example.moviebooking.theater.dto.req.TheatreRequestDTO;
import com.example.moviebooking.theater.dto.res.TheatreResponseDTO;
import com.example.moviebooking.theater.entity.Location;
import com.example.moviebooking.theater.entity.Screen;
import com.example.moviebooking.theater.entity.SeatLayout;
import com.example.moviebooking.theater.entity.Theatre;
import com.example.moviebooking.theater.repository.TheatreRepository;
import com.example.moviebooking.theater.service.TheatreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;
    private final CityRepository cityRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository,
                              CityRepository cityRepository) {
        this.theatreRepository = theatreRepository;
        this.cityRepository = cityRepository;
    }

    // ================= CREATE THEATRE =================
    @Override
    public TheatreResponseDTO createTheatre(TheatreRequestDTO dto) {

        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        Theatre theatre = new Theatre();
        theatre.setName(dto.getName());
        theatre.setCity(city);
        theatre.setActive(false); // admin approval required

        // Location
        Location location = new Location(
                dto.getLocation().getAddress(),
                dto.getLocation().getArea()
        );
        theatre.setLocation(location);

        // Screens
        List<Screen> screens = dto.getScreens().stream().map(screenDTO -> {

            Screen screen = new Screen();
            screen.setName(screenDTO.getName());
            screen.setType(screenDTO.getType());

            SeatLayout seatLayout = new SeatLayout();
            seatLayout.setSeatsPerCategory(
                    screenDTO.getSeatLayout().getSeatsPerCategory()
            );

            int totalSeats = seatLayout.getSeatsPerCategory()
                    .values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            screen.setTotalSeats(totalSeats);
            screen.setSeatLayout(seatLayout);
            screen.setTheatre(theatre);

            return screen;

        }).collect(Collectors.toList());

        theatre.setScreens(screens);

        Theatre saved = theatreRepository.save(theatre);
        return mapToResponse(saved);
    }

    // ================= GET THEATRE =================
    @Override
    public TheatreResponseDTO getTheatreById(Long id) {
        return theatreRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

    // ================= GET ALL =================
    @Override
    public List<TheatreResponseDTO> getAllTheatres() {
        return theatreRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ================= APPROVE THEATRE =================
    @Override
    public void approveTheatre(Long id) {
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
        theatre.setActive(true);
        theatreRepository.save(theatre);
    }

    // ================= MAPPER =================
    private TheatreResponseDTO mapToResponse(Theatre theatre) {
        return new TheatreResponseDTO(
                theatre.getId(),
                theatre.getName(),
                theatre.getCity().getName(),
                new LocationDTO(
                        theatre.getLocation().getAddress(),
                        theatre.getLocation().getArea()
                ),
                theatre.getActive()
        );
    }
}

