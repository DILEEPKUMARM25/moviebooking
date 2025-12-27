package com.example.moviebooking.payment.service;

import com.example.moviebooking.common.exception.DataNotFoundException;
import com.example.moviebooking.payment.entity.Payment;

import java.util.Map;

public interface PaymentService {

    Map<String, Object> createRazorpayOrder(Long bookingId) throws DataNotFoundException;

    Payment verifyPayment(
            Long bookingId,
            String razorpayPaymentId,
            String razorpayOrderId,
            String razorpaySignature
    ) throws DataNotFoundException;
}

