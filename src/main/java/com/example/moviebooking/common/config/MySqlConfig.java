package com.example.moviebooking.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.example.moviebooking.payment.repository",
                "com.example.moviebooking.theater.repository",
                "com.example.moviebooking.user.repository",
                "com.example.moviebooking.city.repository",
                "com.example.moviebooking.booking.repository",
                "com.example.moviebooking.show.repository",
                "com.example.moviebooking.showseat.repository"
        }
)
@EntityScan(
        basePackages = {
                "com.example.moviebooking.payment.entity",
                "com.example.moviebooking.theater.entity",
                "com.example.moviebooking.user.entity",
                "com.example.moviebooking.city.entity",
                "com.example.moviebooking.booking.entity",
                "com.example.moviebooking.show.entity",
                "com.example.moviebooking.showseat.entity"
        }
)
public class MySqlConfig {
    // No extra beans needed for basic JPA setup
}


