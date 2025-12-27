package com.example.moviebooking.common.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.moviebooking.movie.repository"

)
public class MongoConfig {
}

