package com.example.moviebooking.city.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "cities",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "slug")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;          // Bengaluru, Chennai

    private boolean enabled;      // visible to users
    private boolean popular;      // shown in popular list

    private int displayOrder;     // ordering in UI


}

