package com.example.moviebooking.payment.repository;

import com.example.moviebooking.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Optional<Payment> findByBookingId(Long bookingId);
}
