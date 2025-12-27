package com.example.moviebooking.payment.entity;

import com.example.moviebooking.common.enums.PaymentProvider;
import com.example.moviebooking.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", length = 30, nullable = false)
    private PaymentProvider provider;


    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String transactionId;

    // getters & setters
}

