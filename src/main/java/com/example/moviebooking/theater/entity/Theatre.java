package com.example.moviebooking.theater.entity;

import com.example.moviebooking.city.entity.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theatres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Embedded
    private Location location;

    @OneToMany(
            mappedBy = "theatre",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Screen> screens;
    private Boolean active;
}
