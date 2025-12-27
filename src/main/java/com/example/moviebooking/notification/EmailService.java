package com.example.moviebooking.notification;

import com.example.moviebooking.booking.entity.Booking;
import com.example.moviebooking.movie.entity.Movie;
import com.example.moviebooking.movie.repository.MovieRepository;
import com.example.moviebooking.show.entity.Show;
import com.example.moviebooking.show.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    public void sendBookingConfirmation(Long userId, Booking booking) {

        Show show = showRepository.findById(booking.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Movie movie = movieRepository.findById(show.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));


        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("hh:mm a");

        String showTime = show.getShowTime().format(formatter);

        NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        String price = currencyFormat.format(booking.getTotalAmount());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("dileepu574@gmail.com");
        msg.setSubject("Booking Confirmed - " + movie.getTitle());

        msg.setText(
                " Movie: " + movie.getTitle() + "\n" +
                        " Show Time: " + showTime + "\n" +
                        " Seat: " + booking.getSeatNumber()
                        + " (" + booking.getSeatCategory() + ")\n" +
                        " Price: " + price + "\n" +
                        " Booking ID: " + booking.getId()
        );

        mailSender.send(msg);
    }

    public void sendBookingCancellation(Long userId, Booking booking) {

        Show show = showRepository.findById(booking.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Movie movie = movieRepository.findById(show.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));


        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("hh:mm a");

        String showTime = show.getShowTime().format(formatter);

        NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        String price = currencyFormat.format(booking.getTotalAmount());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("dileepu574@gmail.com");
        msg.setSubject("Booking Cancelled - " + movie.getTitle());

        msg.setText(
                " Your booking has been cancelled.\n\n" +
                        " Movie: " + movie.getTitle() + "\n" +
                        " Show Time: " + showTime + "\n" +
                        " Seat: " + booking.getSeatNumber()
                        + " (" + booking.getSeatCategory() + ")\n" +
                        " Amount: " + price + "\n" +
                        " Booking ID: " + booking.getId()
        );

        mailSender.send(msg);
    }
}
